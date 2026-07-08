package com.service.impl.storage;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.storage.StorageTestResultDao;
import com.entity.storage.StorageTestResultEntity;
import com.service.storage.StorageTestResultService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("storageTestResultService")
public class StorageTestResultServiceImpl extends ServiceImpl<StorageTestResultDao, StorageTestResultEntity> implements StorageTestResultService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<StorageTestResultEntity> page = this.selectPage(
                new Query<StorageTestResultEntity>(params).getPage(),
                new EntityWrapper<StorageTestResultEntity>()
        );
        return new PageUtils(page);
    }
}
