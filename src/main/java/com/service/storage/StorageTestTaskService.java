package com.service.storage;

import com.baomidou.mybatisplus.service.IService;
import com.entity.storage.StorageTestTaskEntity;
import com.utils.PageUtils;

import java.util.Map;

public interface StorageTestTaskService extends IService<StorageTestTaskEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
