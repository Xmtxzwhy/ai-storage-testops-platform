package com.service.storage;

import com.baomidou.mybatisplus.service.IService;
import com.entity.storage.StorageTestResultEntity;
import com.utils.PageUtils;

import java.util.Map;

public interface StorageTestResultService extends IService<StorageTestResultEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
