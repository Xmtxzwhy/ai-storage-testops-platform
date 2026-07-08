package com.service.impl.storage;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.storage.StorageAgentRequestDao;
import com.entity.storage.StorageAgentRequestEntity;
import com.service.storage.StorageAgentRequestService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("storageAgentRequestService")
public class StorageAgentRequestServiceImpl extends ServiceImpl<StorageAgentRequestDao, StorageAgentRequestEntity> implements StorageAgentRequestService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<StorageAgentRequestEntity> page = this.selectPage(
                new Query<StorageAgentRequestEntity>(params).getPage(),
                new EntityWrapper<StorageAgentRequestEntity>()
        );
        return new PageUtils(page);
    }
}
