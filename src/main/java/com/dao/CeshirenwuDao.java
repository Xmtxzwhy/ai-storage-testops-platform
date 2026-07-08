package com.dao;

import com.entity.CeshirenwuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.CeshirenwuVO;
import com.entity.view.CeshirenwuView;


/**
 * 测试任务
 * 
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public interface CeshirenwuDao extends BaseMapper<CeshirenwuEntity> {
	
	List<CeshirenwuVO> selectListVO(@Param("ew") Wrapper<CeshirenwuEntity> wrapper);
	
	CeshirenwuVO selectVO(@Param("ew") Wrapper<CeshirenwuEntity> wrapper);
	
	List<CeshirenwuView> selectListView(@Param("ew") Wrapper<CeshirenwuEntity> wrapper);

	List<CeshirenwuView> selectListView(Pagination page,@Param("ew") Wrapper<CeshirenwuEntity> wrapper);

	
	CeshirenwuView selectView(@Param("ew") Wrapper<CeshirenwuEntity> wrapper);
	

}
