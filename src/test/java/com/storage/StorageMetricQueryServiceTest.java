package com.storage;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dto.storage.MetricQueryRequest;
import com.dto.storage.MetricQueryResult;
import com.entity.storage.StorageSampleEntity;
import com.entity.storage.StorageTestResultEntity;
import com.service.impl.storage.StorageMetricQueryServiceImpl;
import com.service.storage.StorageSampleService;
import com.service.storage.StorageTestResultService;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StorageMetricQueryServiceTest {
    @Test
    public void queryBuildsReadableChineseAnswerWithSampleAndVersion() {
        StorageTestResultService resultService = mock(StorageTestResultService.class);
        StorageSampleService sampleService = mock(StorageSampleService.class);

        StorageTestResultEntity row = new StorageTestResultEntity();
        row.setSampleId(2L);
        row.setSuite("CDM");
        row.setScene("clean");
        row.setMetricName("SEQ R 1M Q8T1");
        row.setAverageValue(new BigDecimal("340.457"));
        row.setUnit("MB/s");
        when(resultService.selectList(any(EntityWrapper.class))).thenReturn(Collections.singletonList(row));

        StorageSampleEntity sample = new StorageSampleEntity();
        sample.setId(2L);
        sample.setSampleCode("WM6000-N38B-256G-204");
        sample.setProjectName("WM6000");
        sample.setFwVersion("V2.0.4");
        when(sampleService.selectById(2L)).thenReturn(sample);

        StorageMetricQueryServiceImpl service = new StorageMetricQueryServiceImpl();
        ReflectionTestUtils.setField(service, "storageTestResultService", resultService);
        ReflectionTestUtils.setField(service, "storageSampleService", sampleService);

        MetricQueryRequest request = new MetricQueryRequest();
        request.setSuite("CDM");
        request.setMetricName("SEQ R 1M Q8T1");
        request.setAggregation("MAX");
        request.setRankLimit(1);

        List<MetricQueryResult> results = service.query(request);

        assertEquals(1, results.size());
        String answer = results.get(0).getAnswer();
        assertTrue(answer.contains("最高"));
        assertTrue(answer.contains("样品 WM6000-N38B-256G-204"));
        assertTrue(answer.contains("版本 V2.0.4"));
        assertTrue(answer.contains("场景 clean"));
        assertTrue(answer.contains("平均值为 340.457 MB/s"));
    }
}
