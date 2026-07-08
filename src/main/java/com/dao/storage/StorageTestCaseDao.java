package com.dao.storage;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.storage.StorageTestCaseEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StorageTestCaseDao extends BaseMapper<StorageTestCaseEntity> {
}
