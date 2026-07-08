package com.dao.storage;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.storage.StorageSampleEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StorageSampleDao extends BaseMapper<StorageSampleEntity> {
}
