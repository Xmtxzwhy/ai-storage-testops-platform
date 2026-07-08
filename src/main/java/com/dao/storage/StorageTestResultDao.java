package com.dao.storage;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.storage.StorageTestResultEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StorageTestResultDao extends BaseMapper<StorageTestResultEntity> {
}
