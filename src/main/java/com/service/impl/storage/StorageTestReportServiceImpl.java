package com.service.impl.storage;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.storage.StorageTestReportDao;
import com.entity.storage.StorageTestReportEntity;
import com.service.storage.StorageTestReportService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("storageTestReportService")
public class StorageTestReportServiceImpl extends ServiceImpl<StorageTestReportDao, StorageTestReportEntity> implements StorageTestReportService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<StorageTestReportEntity> page = this.selectPage(
                new Query<StorageTestReportEntity>(params).getPage(),
                new EntityWrapper<StorageTestReportEntity>()
        );
        return new PageUtils(page);
    }
}
