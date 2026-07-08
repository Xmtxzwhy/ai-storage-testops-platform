package com.storage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.entity.storage.StorageReportDatasetEntity;
import com.entity.storage.StorageSampleEntity;
import com.entity.storage.StorageTestReportEntity;
import com.entity.storage.StorageTestResultEntity;
import com.service.impl.storage.StorageReportGeneratorServiceImpl;
import com.service.storage.StorageReportDatasetService;
import com.service.storage.StorageSampleService;
import com.service.storage.StorageTestReportService;
import com.service.storage.StorageTestResultService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StorageReportGeneratorServiceTest {
    private static final File REPORT_FILE = new File("upload/reports/storage-report-10.xlsx");

    @AfterEach
    public void cleanupReportFile() {
        if (REPORT_FILE.exists()) {
            REPORT_FILE.delete();
        }
    }

    @Test
    public void generateMarksBaselineZeroAsNotAvailableInPreviewAndWorkbook() {
        StorageTestReportService reportService = mock(StorageTestReportService.class);
        StorageReportDatasetService datasetService = mock(StorageReportDatasetService.class);
        StorageSampleService sampleService = mock(StorageSampleService.class);
        StorageTestResultService resultService = mock(StorageTestResultService.class);

        StorageTestReportEntity report = new StorageTestReportEntity();
        report.setId(10L);
        report.setReportName("storage comparison");
        report.setReportType("COMPARISON");
        report.setReportStatus("DRAFT");
        when(reportService.selectById(10L)).thenReturn(report);
        when(reportService.updateById(any(StorageTestReportEntity.class))).thenReturn(true);

        StorageReportDatasetEntity targetDataset = dataset(1L, 10L, "TARGET", 2L);
        StorageReportDatasetEntity baselineDataset = dataset(2L, 10L, "BASELINE", 1L);
        when(datasetService.selectList(any(EntityWrapper.class))).thenReturn(Arrays.asList(targetDataset, baselineDataset));

        StorageSampleEntity targetSample = sample(2L, "WM6000", "V2.0.4");
        StorageSampleEntity baselineSample = sample(1L, "WM6000", "V2.0.3");
        when(sampleService.selectById(2L)).thenReturn(targetSample);
        when(sampleService.selectById(1L)).thenReturn(baselineSample);

        StorageTestResultEntity targetResult = result(2L, "CDM", "clean", "SEQ R 1M Q8T1", "340.457");
        StorageTestResultEntity zeroBaselineResult = result(1L, "CDM", "clean", "SEQ R 1M Q8T1", "0");
        when(resultService.selectList(any(EntityWrapper.class)))
                .thenReturn(Collections.singletonList(targetResult))
                .thenReturn(Collections.singletonList(zeroBaselineResult));

        StorageReportGeneratorServiceImpl service = new StorageReportGeneratorServiceImpl();
        ReflectionTestUtils.setField(service, "storageTestReportService", reportService);
        ReflectionTestUtils.setField(service, "storageReportDatasetService", datasetService);
        ReflectionTestUtils.setField(service, "storageSampleService", sampleService);
        ReflectionTestUtils.setField(service, "storageTestResultService", resultService);

        StorageTestReportEntity generated = service.generate(10L);

        assertEquals("COMPLETED", generated.getReportStatus());
        assertNotNull(generated.getGeneratedTime());
        assertEquals("upload/reports/storage-report-10.xlsx", generated.getExcelFilePath());
        assertTrue(REPORT_FILE.exists());
        assertEquals("metrics=1, pass=0, warning=0, fail=0, na=1", generated.getSummary());

        JSONObject preview = JSON.parseObject(generated.getPreviewJson());
        JSONArray rows = preview.getJSONArray("rows");
        assertEquals(1, rows.size());
        JSONObject row = rows.getJSONObject(0);
        assertEquals("N/A", row.getString("baselineAverage"));
        assertEquals("N/A", row.getString("deltaDisplay"));
        assertEquals("N/A", row.getString("status"));
        assertEquals("340.457", row.getString("targetAverage"));
    }

    private StorageReportDatasetEntity dataset(Long id, Long reportId, String role, Long sampleId) {
        StorageReportDatasetEntity dataset = new StorageReportDatasetEntity();
        dataset.setId(id);
        dataset.setReportId(reportId);
        dataset.setDatasetRole(role);
        dataset.setLabel(role + " sample");
        dataset.setSampleId(sampleId);
        return dataset;
    }

    private StorageSampleEntity sample(Long id, String projectName, String fwVersion) {
        StorageSampleEntity sample = new StorageSampleEntity();
        sample.setId(id);
        sample.setProjectName(projectName);
        sample.setFwVersion(fwVersion);
        sample.setSampleCode(projectName + "-" + fwVersion);
        return sample;
    }

    private StorageTestResultEntity result(Long sampleId, String suite, String scene, String metricName, String averageValue) {
        StorageTestResultEntity result = new StorageTestResultEntity();
        result.setSampleId(sampleId);
        result.setSuite(suite);
        result.setScene(scene);
        result.setMetricName(metricName);
        result.setAverageValue(new BigDecimal(averageValue));
        result.setUnit("MB/s");
        return result;
    }
}
