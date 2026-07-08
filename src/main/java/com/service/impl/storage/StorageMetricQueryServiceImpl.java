package com.service.impl.storage;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dto.storage.MetricQueryRequest;
import com.dto.storage.MetricQueryResult;
import com.entity.storage.StorageSampleEntity;
import com.entity.storage.StorageTestResultEntity;
import com.service.storage.StorageMetricQueryService;
import com.service.storage.StorageSampleService;
import com.service.storage.StorageTestResultService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("storageMetricQueryService")
public class StorageMetricQueryServiceImpl implements StorageMetricQueryService {
    @Autowired
    private StorageTestResultService storageTestResultService;
    @Autowired
    private StorageSampleService storageSampleService;

    @Override
    public List<MetricQueryResult> query(MetricQueryRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("指标查询请求不能为空");
        }
        if (StringUtils.isBlank(request.getSuite())) {
            throw new IllegalArgumentException("suite不能为空");
        }
        if (StringUtils.isBlank(request.getMetricName())) {
            throw new IllegalArgumentException("metricName不能为空");
        }

        int rankLimit = request.getRankLimit() == null || request.getRankLimit().intValue() <= 0 ? 1 : request.getRankLimit().intValue();
        String aggregation = StringUtils.defaultIfBlank(request.getAggregation(), "MAX").trim().toUpperCase();
        boolean asc = "MIN".equals(aggregation);

        List<StorageTestResultEntity> rows = storageTestResultService.selectList(
                new EntityWrapper<StorageTestResultEntity>()
                        .eq("suite", request.getSuite().trim())
                        .eq("metric_name", request.getMetricName().trim())
                        .isNotNull("average_value")
                        .orderBy("average_value", asc)
                        .last("limit " + rankLimit)
        );
        if (rows == null || rows.isEmpty()) {
            return Collections.emptyList();
        }

        List<MetricQueryResult> results = new ArrayList<MetricQueryResult>();
        for (StorageTestResultEntity row : rows) {
            StorageSampleEntity sample = row.getSampleId() == null ? null : storageSampleService.selectById(row.getSampleId());

            MetricQueryResult result = new MetricQueryResult();
            result.setSampleId(row.getSampleId());
            result.setSampleCode(sample == null ? null : sample.getSampleCode());
            result.setProjectName(sample == null ? null : sample.getProjectName());
            result.setFwVersion(sample == null ? null : sample.getFwVersion());
            result.setScene(row.getScene());
            result.setSuite(row.getSuite());
            result.setMetricName(row.getMetricName());
            result.setAverageValue(row.getAverageValue());
            result.setUnit(row.getUnit());
            result.setAnswer(buildAnswer(result, aggregation));
            results.add(result);
        }
        return results;
    }

    private String buildAnswer(MetricQueryResult result, String aggregation) {
        String rankingWord = "MIN".equals(aggregation) ? "最低" : "最高";
        String sampleCode = StringUtils.defaultIfBlank(result.getSampleCode(), "未知样品");
        String fwVersion = StringUtils.defaultIfBlank(result.getFwVersion(), "未知版本");
        String scene = StringUtils.defaultIfBlank(result.getScene(), "未知场景");
        String unit = StringUtils.defaultIfBlank(result.getUnit(), "");
        BigDecimal averageValue = result.getAverageValue() == null ? BigDecimal.ZERO : result.getAverageValue();

        return String.format("%s 的 %s 平均值%s的是样品 %s，版本 %s，场景 %s，平均值为 %s %s。",
                StringUtils.defaultIfBlank(result.getSuite(), ""),
                StringUtils.defaultIfBlank(result.getMetricName(), ""),
                rankingWord,
                sampleCode,
                fwVersion,
                scene,
                averageValue.toPlainString(),
                unit);
    }
}
