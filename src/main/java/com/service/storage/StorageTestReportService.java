package com.service.storage;

import com.baomidou.mybatisplus.service.IService;
import com.entity.storage.StorageTestReportEntity;
import com.utils.PageUtils;

import java.util.Map;

public interface StorageTestReportService extends IService<StorageTestReportEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
