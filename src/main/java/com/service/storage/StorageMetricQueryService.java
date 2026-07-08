package com.service.storage;

import com.dto.storage.MetricQueryRequest;
import com.dto.storage.MetricQueryResult;

import java.util.List;

public interface StorageMetricQueryService {
    List<MetricQueryResult> query(MetricQueryRequest request);
}
