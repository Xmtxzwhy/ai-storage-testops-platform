package com.service.storage;

import com.baomidou.mybatisplus.service.IService;
import com.entity.storage.StorageTestNodeEntity;
import com.utils.PageUtils;

import java.util.Map;

public interface StorageTestNodeService extends IService<StorageTestNodeEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
