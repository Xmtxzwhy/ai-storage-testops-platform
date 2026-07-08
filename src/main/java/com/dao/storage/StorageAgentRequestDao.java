package com.dao.storage;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.storage.StorageAgentRequestEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StorageAgentRequestDao extends BaseMapper<StorageAgentRequestEntity> {
}
