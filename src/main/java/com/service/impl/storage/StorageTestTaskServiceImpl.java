package com.service.impl.storage;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.storage.StorageTestTaskDao;
import com.entity.storage.StorageTestTaskEntity;
import com.service.storage.StorageTestTaskService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("storageTestTaskService")
public class StorageTestTaskServiceImpl extends ServiceImpl<StorageTestTaskDao, StorageTestTaskEntity> implements StorageTestTaskService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<StorageTestTaskEntity> page = this.selectPage(
                new Query<StorageTestTaskEntity>(params).getPage(),
                new EntityWrapper<StorageTestTaskEntity>()
        );
        return new PageUtils(page);
    }
}
