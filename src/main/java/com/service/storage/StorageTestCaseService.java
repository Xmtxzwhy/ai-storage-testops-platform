package com.service.storage;

import com.baomidou.mybatisplus.service.IService;
import com.entity.storage.StorageTestCaseEntity;
import com.utils.PageUtils;

import java.util.Map;

public interface StorageTestCaseService extends IService<StorageTestCaseEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
