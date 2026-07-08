package com.dao.storage;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.storage.StorageTestTaskEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StorageTestTaskDao extends BaseMapper<StorageTestTaskEntity> {
}
