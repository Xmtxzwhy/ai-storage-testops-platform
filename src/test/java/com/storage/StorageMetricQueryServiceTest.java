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
        sample.setSampleCode("Project-A-Flash-X-256G-FW-v2");
        sample.setProjectName("Project-A");
        sample.setFwVersion("FW-v2");
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
        MetricQueryResult result = results.get(0);
        assertEquals("Project-A-Flash-X-256G-FW-v2", result.getSampleCode());
        assertEquals("Project-A", result.getProjectName());
        assertEquals("FW-v2", result.getFwVersion());
        assertEquals("clean", result.getScene());
        assertEquals(new BigDecimal("340.457"), result.getAverageValue());
        assertTrue(result.getAnswer().contains("Project-A-Flash-X-256G-FW-v2"));
        assertTrue(result.getAnswer().contains("FW-v2"));
    }
}
