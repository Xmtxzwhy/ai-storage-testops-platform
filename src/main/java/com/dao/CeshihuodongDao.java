package com.dao;

import com.entity.CeshihuodongEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.CeshihuodongVO;
import com.entity.view.CeshihuodongView;


/**
 * 测试活动
 * 
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public interface CeshihuodongDao extends BaseMapper<CeshihuodongEntity> {
	
	List<CeshihuodongVO> selectListVO(@Param("ew") Wrapper<CeshihuodongEntity> wrapper);
	
	CeshihuodongVO selectVO(@Param("ew") Wrapper<CeshihuodongEntity> wrapper);
	
	List<CeshihuodongView> selectListView(@Param("ew") Wrapper<CeshihuodongEntity> wrapper);

	List<CeshihuodongView> selectListView(Pagination page,@Param("ew") Wrapper<CeshihuodongEntity> wrapper);

	
	CeshihuodongView selectView(@Param("ew") Wrapper<CeshihuodongEntity> wrapper);
	

}
