package com.service.impl.storage;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.storage.StorageSampleDao;
import com.entity.storage.StorageSampleEntity;
import com.service.storage.StorageSampleService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("storageSampleService")
public class StorageSampleServiceImpl extends ServiceImpl<StorageSampleDao, StorageSampleEntity> implements StorageSampleService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<StorageSampleEntity> page = this.selectPage(
                new Query<StorageSampleEntity>(params).getPage(),
                new EntityWrapper<StorageSampleEntity>()
        );
        return new PageUtils(page);
    }
}
