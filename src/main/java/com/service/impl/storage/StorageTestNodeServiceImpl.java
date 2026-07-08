package com.service.impl.storage;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.storage.StorageTestNodeDao;
import com.entity.storage.StorageTestNodeEntity;
import com.service.storage.StorageTestNodeService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("storageTestNodeService")
public class StorageTestNodeServiceImpl extends ServiceImpl<StorageTestNodeDao, StorageTestNodeEntity> implements StorageTestNodeService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<StorageTestNodeEntity> page = this.selectPage(
                new Query<StorageTestNodeEntity>(params).getPage(),
                new EntityWrapper<StorageTestNodeEntity>()
        );
        return new PageUtils(page);
    }
}
