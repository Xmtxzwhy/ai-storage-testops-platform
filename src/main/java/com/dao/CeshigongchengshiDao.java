package com.dao;

import com.entity.CeshigongchengshiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.CeshigongchengshiVO;
import com.entity.view.CeshigongchengshiView;


/**
 * 测试工程师
 * 
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public interface CeshigongchengshiDao extends BaseMapper<CeshigongchengshiEntity> {
	
	List<CeshigongchengshiVO> selectListVO(@Param("ew") Wrapper<CeshigongchengshiEntity> wrapper);
	
	CeshigongchengshiVO selectVO(@Param("ew") Wrapper<CeshigongchengshiEntity> wrapper);
	
	List<CeshigongchengshiView> selectListView(@Param("ew") Wrapper<CeshigongchengshiEntity> wrapper);

	List<CeshigongchengshiView> selectListView(Pagination page,@Param("ew") Wrapper<CeshigongchengshiEntity> wrapper);

	
	CeshigongchengshiView selectView(@Param("ew") Wrapper<CeshigongchengshiEntity> wrapper);
	

}
