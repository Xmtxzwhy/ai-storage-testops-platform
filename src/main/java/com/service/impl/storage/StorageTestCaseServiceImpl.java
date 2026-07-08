package com.service.impl.storage;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.storage.StorageTestCaseDao;
import com.entity.storage.StorageTestCaseEntity;
import com.service.storage.StorageTestCaseService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("storageTestCaseService")
public class StorageTestCaseServiceImpl extends ServiceImpl<StorageTestCaseDao, StorageTestCaseEntity> implements StorageTestCaseService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<StorageTestCaseEntity> page = this.selectPage(
                new Query<StorageTestCaseEntity>(params).getPage(),
                new EntityWrapper<StorageTestCaseEntity>()
        );
        return new PageUtils(page);
    }
}
