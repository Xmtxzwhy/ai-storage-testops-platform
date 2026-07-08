package com.service.storage;

import com.baomidou.mybatisplus.service.IService;
import com.entity.storage.StorageAgentRequestEntity;
import com.utils.PageUtils;

import java.util.Map;

public interface StorageAgentRequestService extends IService<StorageAgentRequestEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
