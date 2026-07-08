package com.dao;

import com.entity.CeshijihuaEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.CeshijihuaVO;
import com.entity.view.CeshijihuaView;


/**
 * 测试计划
 * 
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public interface CeshijihuaDao extends BaseMapper<CeshijihuaEntity> {
	
	List<CeshijihuaVO> selectListVO(@Param("ew") Wrapper<CeshijihuaEntity> wrapper);
	
	CeshijihuaVO selectVO(@Param("ew") Wrapper<CeshijihuaEntity> wrapper);
	
	List<CeshijihuaView> selectListView(@Param("ew") Wrapper<CeshijihuaEntity> wrapper);

	List<CeshijihuaView> selectListView(Pagination page,@Param("ew") Wrapper<CeshijihuaEntity> wrapper);

	CeshijihuaView selectView(@Param("ew") Wrapper<CeshijihuaEntity> wrapper);
	

}
