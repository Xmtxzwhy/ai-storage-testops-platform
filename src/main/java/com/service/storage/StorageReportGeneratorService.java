package com.service.storage;

import com.dto.storage.CreateStorageReportRequest;
import com.entity.storage.StorageTestReportEntity;

import java.io.File;
import java.util.Map;

public interface StorageReportGeneratorService {
    StorageTestReportEntity createReport(CreateStorageReportRequest request);

    StorageTestReportEntity generate(Long reportId);

    Map<String, Object> getReportDetail(Long reportId);

    File getReportFile(Long reportId);
}
