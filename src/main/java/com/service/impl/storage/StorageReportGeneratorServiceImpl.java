package com.service.impl.storage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dto.storage.CreateStorageReportRequest;
import com.entity.storage.StorageReportDatasetEntity;
import com.entity.storage.StorageSampleEntity;
import com.entity.storage.StorageTestReportEntity;
import com.entity.storage.StorageTestResultEntity;
import com.service.storage.StorageReportDatasetService;
import com.service.storage.StorageReportGeneratorService;
import com.service.storage.StorageSampleService;
import com.service.storage.StorageTestReportService;
import com.service.storage.StorageTestResultService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("storageReportGeneratorService")
public class StorageReportGeneratorServiceImpl implements StorageReportGeneratorService {
    private static final String TEMPLATE_PATH = "templates/storage_performance_report_template.xlsx";
    private static final String OUTPUT_DIR = "upload/reports";
    private static final String TARGET_ROLE = "TARGET";
    private static final String BASELINE_ROLE = "BASELINE";
    private static final String COMPETITOR_ROLE = "COMPETITOR";

    @Autowired
    private StorageTestReportService storageTestReportService;
    @Autowired
    private StorageReportDatasetService storageReportDatasetService;
    @Autowired
    private StorageSampleService storageSampleService;
    @Autowired
    private StorageTestResultService storageTestResultService;

    @Override
    public StorageTestReportEntity createReport(CreateStorageReportRequest request) {
        validateCreateRequest(request);

        StorageTestReportEntity report = new StorageTestReportEntity();
        report.setReportName(request.getReportName().trim());
        report.setReportType(StringUtils.defaultIfBlank(request.getReportType(), "COMPARISON").trim());
        report.setReportStatus("DRAFT");
        report.setPreviewJson(null);
        report.setExcelFilePath(null);
        report.setSummary(null);
        report.setGeneratedTime(null);
        report.setErrorMessage(null);
        storageTestReportService.insert(report);

        for (int i = 0; i < request.getSampleIds().size(); i++) {
            StorageSampleEntity sample = requireSample(request.getSampleIds().get(i));
            StorageReportDatasetEntity dataset = new StorageReportDatasetEntity();
            dataset.setReportId(report.getId());
            dataset.setDatasetRole(resolveDatasetRole(i));
            dataset.setLabel(buildDatasetLabel(sample));
            dataset.setProjectName(sample.getProjectName());
            dataset.setFwVersion(sample.getFwVersion());
            dataset.setSoc(sample.getSoc());
            dataset.setParticle(sample.getParticle());
            dataset.setCapacity(sample.getCapacity());
            dataset.setSampleId(sample.getId());
            dataset.setQueryFiltersJson(buildDatasetFilterJson(sample));
            storageReportDatasetService.insert(dataset);
        }

        return storageTestReportService.selectById(report.getId());
    }

    @Override
    public StorageTestReportEntity generate(Long reportId) {
        StorageTestReportEntity report = requireReport(reportId);
        List<StorageReportDatasetEntity> datasets = listDatasets(reportId);
        if (datasets.isEmpty()) {
            throw new IllegalArgumentException("storage report dataset is empty");
        }

        StorageReportDatasetEntity targetDataset = datasets.get(0);
        StorageReportDatasetEntity baselineDataset = datasets.size() > 1 ? datasets.get(1) : null;
        List<StorageReportDatasetEntity> competitorDatasets = datasets.size() > 2
                ? new ArrayList<StorageReportDatasetEntity>(datasets.subList(2, datasets.size()))
                : Collections.<StorageReportDatasetEntity>emptyList();

        report.setReportStatus("GENERATING");
        report.setErrorMessage(null);
        storageTestReportService.updateById(report);

        try {
            StorageSampleEntity targetSample = requireSample(targetDataset.getSampleId());
            StorageSampleEntity baselineSample = baselineDataset == null ? null : requireSample(baselineDataset.getSampleId());

            Map<String, StorageTestResultEntity> targetResults = indexResultsByMetric(loadSampleResults(targetSample.getId()));
            Map<String, StorageTestResultEntity> baselineResults = baselineSample == null
                    ? Collections.<String, StorageTestResultEntity>emptyMap()
                    : indexResultsByMetric(loadSampleResults(baselineSample.getId()));

            List<ReportMetricRow> metricRows = buildMetricRows(targetResults, baselineResults);
            String previewJson = buildPreviewJson(report, datasets, targetSample, baselineSample, competitorDatasets, metricRows);
            File excelFile = writeWorkbook(report, datasets, metricRows);

            report.setPreviewJson(previewJson);
            report.setExcelFilePath(excelFile.getPath().replace('\\', '/'));
            report.setReportStatus("COMPLETED");
            report.setGeneratedTime(new Date());
            report.setErrorMessage(null);
            report.setSummary(buildSummary(metricRows));
            storageTestReportService.updateById(report);
            return storageTestReportService.selectById(reportId);
        } catch (RuntimeException ex) {
            markFailed(report, ex.getMessage());
            throw ex;
        } catch (IOException ex) {
            markFailed(report, ex.getMessage());
            throw new IllegalStateException("failed to generate storage report excel", ex);
        }
    }

    @Override
    public Map<String, Object> getReportDetail(Long reportId) {
        StorageTestReportEntity report = requireReport(reportId);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("report", report);
        data.put("datasets", listDatasets(reportId));
        data.put("preview", StringUtils.isBlank(report.getPreviewJson()) ? null : JSON.parse(report.getPreviewJson()));
        return data;
    }

    @Override
    public File getReportFile(Long reportId) {
        StorageTestReportEntity report = requireReport(reportId);
        if (StringUtils.isBlank(report.getExcelFilePath())) {
            throw new IllegalArgumentException("report excel file has not been generated");
        }
        File file = new File(report.getExcelFilePath());
        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException("report excel file does not exist");
        }
        return file;
    }

    private void validateCreateRequest(CreateStorageReportRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("storage report request cannot be null");
        }
        if (StringUtils.isBlank(request.getReportName())) {
            throw new IllegalArgumentException("reportName cannot be blank");
        }
        if (request.getSampleIds() == null || request.getSampleIds().isEmpty()) {
            throw new IllegalArgumentException("sampleIds cannot be empty");
        }
    }

    private StorageTestReportEntity requireReport(Long reportId) {
        if (reportId == null) {
            throw new IllegalArgumentException("reportId cannot be null");
        }
        StorageTestReportEntity report = storageTestReportService.selectById(reportId);
        if (report == null) {
            throw new IllegalArgumentException("storage report does not exist");
        }
        return report;
    }

    private StorageSampleEntity requireSample(Long sampleId) {
        if (sampleId == null) {
            throw new IllegalArgumentException("sampleId cannot be null");
        }
        StorageSampleEntity sample = storageSampleService.selectById(sampleId);
        if (sample == null) {
            throw new IllegalArgumentException("storage sample does not exist: " + sampleId);
        }
        return sample;
    }

    private List<StorageReportDatasetEntity> listDatasets(Long reportId) {
        List<StorageReportDatasetEntity> datasets = storageReportDatasetService.selectList(
                new EntityWrapper<StorageReportDatasetEntity>()
                        .eq("report_id", reportId)
                        .orderBy("id", true)
        );
        return datasets == null ? Collections.<StorageReportDatasetEntity>emptyList() : datasets;
    }

    private String resolveDatasetRole(int index) {
        if (index == 0) {
            return TARGET_ROLE;
        }
        if (index == 1) {
            return BASELINE_ROLE;
        }
        return COMPETITOR_ROLE;
    }

    private String buildDatasetLabel(StorageSampleEntity sample) {
        StringBuilder builder = new StringBuilder();
        builder.append(StringUtils.defaultIfBlank(sample.getProjectName(), "Sample"));
        if (StringUtils.isNotBlank(sample.getSampleCode())) {
            builder.append(" ").append(sample.getSampleCode().trim());
        }
        if (StringUtils.isNotBlank(sample.getFwVersion())) {
            builder.append(" ").append(sample.getFwVersion().trim());
        }
        return builder.toString().trim();
    }

    private String buildDatasetFilterJson(StorageSampleEntity sample) {
        Map<String, Object> filter = new LinkedHashMap<String, Object>();
        filter.put("sampleId", sample.getId());
        filter.put("projectName", sample.getProjectName());
        filter.put("fwVersion", sample.getFwVersion());
        filter.put("soc", sample.getSoc());
        filter.put("particle", sample.getParticle());
        filter.put("capacity", sample.getCapacity());
        return JSON.toJSONString(filter);
    }

    private List<StorageTestResultEntity> loadSampleResults(Long sampleId) {
        List<StorageTestResultEntity> rows = storageTestResultService.selectList(
                new EntityWrapper<StorageTestResultEntity>()
                        .eq("sample_id", sampleId)
                        .isNotNull("average_value")
        );
        List<StorageTestResultEntity> result = rows == null ? new ArrayList<StorageTestResultEntity>() : rows;
        Collections.sort(result, new Comparator<StorageTestResultEntity>() {
            @Override
            public int compare(StorageTestResultEntity left, StorageTestResultEntity right) {
                return buildMetricKey(left).compareTo(buildMetricKey(right));
            }
        });
        return result;
    }

    private Map<String, StorageTestResultEntity> indexResultsByMetric(List<StorageTestResultEntity> rows) {
        Map<String, StorageTestResultEntity> resultMap = new LinkedHashMap<String, StorageTestResultEntity>();
        for (StorageTestResultEntity row : rows) {
            resultMap.put(buildMetricKey(row), row);
        }
        return resultMap;
    }

    private List<ReportMetricRow> buildMetricRows(Map<String, StorageTestResultEntity> targetResults,
                                                  Map<String, StorageTestResultEntity> baselineResults) {
        List<ReportMetricRow> rows = new ArrayList<ReportMetricRow>();
        for (Map.Entry<String, StorageTestResultEntity> entry : targetResults.entrySet()) {
            StorageTestResultEntity targetResult = entry.getValue();
            StorageTestResultEntity baselineResult = baselineResults.get(entry.getKey());
            rows.add(buildMetricRow(targetResult, baselineResult));
        }
        return rows;
    }

    private ReportMetricRow buildMetricRow(StorageTestResultEntity targetResult, StorageTestResultEntity baselineResult) {
        ReportMetricRow row = new ReportMetricRow();
        row.setSuite(targetResult.getSuite());
        row.setScene(targetResult.getScene());
        row.setMetricName(targetResult.getMetricName());
        row.setTargetAverage(targetResult.getAverageValue());
        row.setUnit(StringUtils.defaultIfBlank(targetResult.getUnit(),
                baselineResult == null ? "MB/s" : baselineResult.getUnit()));

        if (baselineResult == null || baselineResult.getAverageValue() == null
                || BigDecimal.ZERO.compareTo(baselineResult.getAverageValue()) == 0) {
            row.setBaselineAverage(null);
            row.setDeltaRatio(null);
            row.setDeltaDisplay("N/A");
            row.setStatus("N/A");
            return row;
        }

        row.setBaselineAverage(baselineResult.getAverageValue());
        BigDecimal deltaRatio = targetResult.getAverageValue()
                .subtract(baselineResult.getAverageValue())
                .divide(baselineResult.getAverageValue(), 4, RoundingMode.HALF_UP);
        row.setDeltaRatio(deltaRatio);
        row.setDeltaDisplay(deltaRatio.multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP).toPlainString() + "%");

        if (deltaRatio.compareTo(new BigDecimal("-0.20")) <= 0) {
            row.setStatus("FAIL");
        } else if (deltaRatio.compareTo(new BigDecimal("-0.10")) <= 0) {
            row.setStatus("WARNING");
        } else {
            row.setStatus("PASS");
        }
        return row;
    }

    private String buildPreviewJson(StorageTestReportEntity report,
                                    List<StorageReportDatasetEntity> datasets,
                                    StorageSampleEntity targetSample,
                                    StorageSampleEntity baselineSample,
                                    List<StorageReportDatasetEntity> competitors,
                                    List<ReportMetricRow> metricRows) {
        Map<String, Object> preview = new LinkedHashMap<String, Object>();
        preview.put("reportId", report.getId());
        preview.put("reportName", report.getReportName());
        preview.put("reportType", report.getReportType());
        preview.put("reportStatus", "COMPLETED");
        preview.put("generatedTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        preview.put("datasets", datasets);
        preview.put("targetSample", targetSample);
        preview.put("baselineSample", baselineSample);
        preview.put("competitorDatasets", competitors);
        preview.put("rows", buildPreviewRows(metricRows));
        preview.put("summary", buildSummary(metricRows));
        return JSON.toJSONString(preview);
    }

    private List<Map<String, Object>> buildPreviewRows(List<ReportMetricRow> metricRows) {
        List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
        for (ReportMetricRow row : metricRows) {
            Map<String, Object> item = new LinkedHashMap<String, Object>();
            item.put("suite", row.getSuite());
            item.put("scene", row.getScene());
            item.put("metricName", row.getMetricName());
            item.put("targetAverage", row.getTargetAverage());
            item.put("baselineAverage", row.getBaselineAverage() == null ? "N/A" : row.getBaselineAverage());
            item.put("deltaRatio", row.getDeltaRatio());
            item.put("deltaDisplay", row.getDeltaDisplay());
            item.put("status", row.getStatus());
            item.put("unit", row.getUnit());
            rows.add(item);
        }
        return rows;
    }

    private String buildSummary(List<ReportMetricRow> metricRows) {
        int failCount = 0;
        int warningCount = 0;
        int passCount = 0;
        int naCount = 0;
        for (ReportMetricRow row : metricRows) {
            if ("FAIL".equals(row.getStatus())) {
                failCount++;
            } else if ("WARNING".equals(row.getStatus())) {
                warningCount++;
            } else if ("PASS".equals(row.getStatus())) {
                passCount++;
            } else {
                naCount++;
            }
        }
        return "metrics=" + metricRows.size()
                + ", pass=" + passCount
                + ", warning=" + warningCount
                + ", fail=" + failCount
                + ", na=" + naCount;
    }

    private File writeWorkbook(StorageTestReportEntity report,
                               List<StorageReportDatasetEntity> datasets,
                               List<ReportMetricRow> metricRows) throws IOException {
        Workbook workbook = openWorkbookTemplate();
        try {
            Sheet sheet = workbook.getNumberOfSheets() > 0 ? workbook.getSheetAt(0) : workbook.createSheet("性能报告");
            if (StringUtils.isBlank(sheet.getSheetName())) {
                workbook.setSheetName(0, "性能报告");
            }

            fillWorkbook(sheet, report, datasets, metricRows);

            File outputDir = new File(OUTPUT_DIR);
            if (!outputDir.exists() && !outputDir.mkdirs()) {
                throw new IOException("failed to create report output directory");
            }

            File outputFile = new File(outputDir, "storage-report-" + report.getId() + ".xlsx");
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            try {
                workbook.write(outputStream);
            } finally {
                outputStream.close();
            }
            return outputFile;
        } finally {
            workbook.close();
        }
    }

    private Workbook openWorkbookTemplate() throws IOException {
        ClassPathResource resource = new ClassPathResource(TEMPLATE_PATH);
        if (resource.exists()) {
            InputStream inputStream = resource.getInputStream();
            try {
                return new XSSFWorkbook(inputStream);
            } finally {
                inputStream.close();
            }
        }

        File templateFile = new File("src/main/resources/" + TEMPLATE_PATH);
        if (templateFile.exists() && templateFile.isFile()) {
            FileInputStream inputStream = new FileInputStream(templateFile);
            try {
                return new XSSFWorkbook(inputStream);
            } finally {
                inputStream.close();
            }
        }
        return createFallbackWorkbook();
    }

    private Workbook createFallbackWorkbook() {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("性能报告");

        setCellValue(sheet, 0, 0, "AI 存储性能测试报告");
        setCellValue(sheet, 2, 0, "报告名称");
        setCellValue(sheet, 2, 1, "");
        setCellValue(sheet, 3, 0, "报告类型");
        setCellValue(sheet, 3, 1, "");
        setCellValue(sheet, 5, 0, "数据集");
        setCellValue(sheet, 9, 0, "测试套件");
        setCellValue(sheet, 9, 1, "场景");
        setCellValue(sheet, 9, 2, "指标");
        setCellValue(sheet, 9, 3, "目标平均值");
        setCellValue(sheet, 9, 4, "基准平均值");
        setCellValue(sheet, 9, 5, "差异");
        setCellValue(sheet, 9, 6, "状态");
        setCellValue(sheet, 29, 0, "异常项");
        setCellValue(sheet, 44, 0, "人工备注");
        setCellValue(sheet, 54, 0, "总结");
        return workbook;
    }

    private void fillWorkbook(Sheet sheet,
                              StorageTestReportEntity report,
                              List<StorageReportDatasetEntity> datasets,
                              List<ReportMetricRow> metricRows) {
        setCellValue(sheet, 2, 1, report.getReportName());
        setCellValue(sheet, 3, 1, report.getReportType());

        int datasetRowStart = 6;
        for (int i = 0; i < datasets.size(); i++) {
            StorageReportDatasetEntity dataset = datasets.get(i);
            String value = dataset.getDatasetRole() + " | "
                    + StringUtils.defaultIfBlank(dataset.getLabel(), "")
                    + " | sampleId=" + dataset.getSampleId();
            setCellValue(sheet, datasetRowStart + i, 0, value);
        }

        int metricRowStart = 10;
        for (int i = 0; i < metricRows.size(); i++) {
            ReportMetricRow row = metricRows.get(i);
            int rowIndex = metricRowStart + i;
            setCellValue(sheet, rowIndex, 0, row.getSuite());
            setCellValue(sheet, rowIndex, 1, row.getScene());
            setCellValue(sheet, rowIndex, 2, row.getMetricName());
            setCellValue(sheet, rowIndex, 3, formatNumber(row.getTargetAverage(), row.getUnit()));
            setCellValue(sheet, rowIndex, 4, row.getBaselineAverage() == null ? "N/A" : formatNumber(row.getBaselineAverage(), row.getUnit()));
            setCellValue(sheet, rowIndex, 5, row.getDeltaDisplay());
            setCellValue(sheet, rowIndex, 6, row.getStatus());
        }

        int exceptionRow = 30;
        int exceptionCount = 0;
        for (ReportMetricRow row : metricRows) {
            if ("FAIL".equals(row.getStatus()) || "WARNING".equals(row.getStatus())) {
                setCellValue(sheet, exceptionRow + exceptionCount, 0,
                        row.getSuite() + " / " + row.getScene() + " / " + row.getMetricName() + " -> " + row.getDeltaDisplay() + " (" + row.getStatus() + ")");
                exceptionCount++;
            }
        }
        if (exceptionCount == 0) {
            setCellValue(sheet, exceptionRow, 0, "No warning or failure items.");
        }

        setCellValue(sheet, 54, 1, buildSummary(metricRows));
        autoSize(sheet, 0, 6);
    }

    private void autoSize(Sheet sheet, int startColumn, int endColumn) {
        for (int i = startColumn; i <= endColumn; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private void setCellValue(Sheet sheet, int rowIndex, int columnIndex, String value) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        Cell cell = row.getCell(columnIndex);
        if (cell == null) {
            cell = row.createCell(columnIndex);
        }
        cell.setCellValue(StringUtils.defaultString(value));
    }

    private String formatNumber(BigDecimal value, String unit) {
        if (value == null) {
            return "N/A";
        }
        String suffix = StringUtils.isBlank(unit) ? "" : " " + unit;
        return value.setScale(3, RoundingMode.HALF_UP).toPlainString() + suffix;
    }

    private String buildMetricKey(StorageTestResultEntity row) {
        return StringUtils.defaultString(row.getSuite()) + "|"
                + StringUtils.defaultString(row.getScene()) + "|"
                + StringUtils.defaultString(row.getMetricName());
    }

    private void markFailed(StorageTestReportEntity report, String message) {
        report.setReportStatus("FAILED");
        report.setGeneratedTime(new Date());
        report.setErrorMessage(StringUtils.abbreviate(StringUtils.defaultString(message), 1000));
        storageTestReportService.updateById(report);
    }

    public static class ReportMetricRow {
        private String suite;
        private String scene;
        private String metricName;
        private BigDecimal targetAverage;
        private BigDecimal baselineAverage;
        private BigDecimal deltaRatio;
        private String deltaDisplay;
        private String status;
        private String unit;

        public String getSuite() {
            return suite;
        }

        public void setSuite(String suite) {
            this.suite = suite;
        }

        public String getScene() {
            return scene;
        }

        public void setScene(String scene) {
            this.scene = scene;
        }

        public String getMetricName() {
            return metricName;
        }

        public void setMetricName(String metricName) {
            this.metricName = metricName;
        }

        public BigDecimal getTargetAverage() {
            return targetAverage;
        }

        public void setTargetAverage(BigDecimal targetAverage) {
            this.targetAverage = targetAverage;
        }

        public BigDecimal getBaselineAverage() {
            return baselineAverage;
        }

        public void setBaselineAverage(BigDecimal baselineAverage) {
            this.baselineAverage = baselineAverage;
        }

        public BigDecimal getDeltaRatio() {
            return deltaRatio;
        }

        public void setDeltaRatio(BigDecimal deltaRatio) {
            this.deltaRatio = deltaRatio;
        }

        public String getDeltaDisplay() {
            return deltaDisplay;
        }

        public void setDeltaDisplay(String deltaDisplay) {
            this.deltaDisplay = deltaDisplay;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }
    }
}
