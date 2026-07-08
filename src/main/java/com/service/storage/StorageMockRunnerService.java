package com.service.storage;

import com.dto.storage.CreateStorageTaskRequest;
import com.entity.storage.StorageTestResultEntity;
import com.entity.storage.StorageTestTaskEntity;

import java.util.List;

public interface StorageMockRunnerService {
    StorageTestTaskEntity createTask(CreateStorageTaskRequest request);

    StorageTestTaskEntity runTask(Long taskId);

    List<StorageTestResultEntity> getTaskResults(Long taskId);
}
