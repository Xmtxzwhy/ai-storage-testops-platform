package com.dao;

import com.entity.FenxishiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.FenxishiVO;
import com.entity.view.FenxishiView;


/**
 * QA分析师
 * 
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public interface FenxishiDao extends BaseMapper<FenxishiEntity> {
	
	List<FenxishiVO> selectListVO(@Param("ew") Wrapper<FenxishiEntity> wrapper);
	
	FenxishiVO selectVO(@Param("ew") Wrapper<FenxishiEntity> wrapper);
	
	List<FenxishiView> selectListView(@Param("ew") Wrapper<FenxishiEntity> wrapper);

	List<FenxishiView> selectListView(Pagination page,@Param("ew") Wrapper<FenxishiEntity> wrapper);

	
	FenxishiView selectView(@Param("ew") Wrapper<FenxishiEntity> wrapper);
	

}
