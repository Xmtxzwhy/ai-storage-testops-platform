package com.service.storage;

import com.baomidou.mybatisplus.service.IService;
import com.entity.storage.StorageReportDatasetEntity;
import com.utils.PageUtils;

import java.util.Map;

public interface StorageReportDatasetService extends IService<StorageReportDatasetEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
