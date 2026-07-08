package com.service.storage;

import com.baomidou.mybatisplus.service.IService;
import com.entity.storage.StorageSampleEntity;
import com.utils.PageUtils;

import java.util.Map;

public interface StorageSampleService extends IService<StorageSampleEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
