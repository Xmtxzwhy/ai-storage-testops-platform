package com.dao;

import com.entity.CeshituanduiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.CeshituanduiVO;
import com.entity.view.CeshituanduiView;


/**
 * 测试团队
 * 
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public interface CeshituanduiDao extends BaseMapper<CeshituanduiEntity> {
	
	List<CeshituanduiVO> selectListVO(@Param("ew") Wrapper<CeshituanduiEntity> wrapper);
	
	CeshituanduiVO selectVO(@Param("ew") Wrapper<CeshituanduiEntity> wrapper);
	
	List<CeshituanduiView> selectListView(@Param("ew") Wrapper<CeshituanduiEntity> wrapper);

	List<CeshituanduiView> selectListView(Pagination page,@Param("ew") Wrapper<CeshituanduiEntity> wrapper);

	
	CeshituanduiView selectView(@Param("ew") Wrapper<CeshituanduiEntity> wrapper);
	

}
