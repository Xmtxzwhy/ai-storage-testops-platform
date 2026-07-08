package com.service.impl.storage;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.storage.StorageReportDatasetDao;
import com.entity.storage.StorageReportDatasetEntity;
import com.service.storage.StorageReportDatasetService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("storageReportDatasetService")
public class StorageReportDatasetServiceImpl extends ServiceImpl<StorageReportDatasetDao, StorageReportDatasetEntity> implements StorageReportDatasetService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<StorageReportDatasetEntity> page = this.selectPage(
                new Query<StorageReportDatasetEntity>(params).getPage(),
                new EntityWrapper<StorageReportDatasetEntity>()
        );
        return new PageUtils(page);
    }
}
