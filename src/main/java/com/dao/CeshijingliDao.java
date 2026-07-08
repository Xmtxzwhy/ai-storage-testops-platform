package com.dao;

import com.entity.CeshijingliEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.CeshijingliVO;
import com.entity.view.CeshijingliView;


/**
 * 测试经理
 * 
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public interface CeshijingliDao extends BaseMapper<CeshijingliEntity> {
	
	List<CeshijingliVO> selectListVO(@Param("ew") Wrapper<CeshijingliEntity> wrapper);
	
	CeshijingliVO selectVO(@Param("ew") Wrapper<CeshijingliEntity> wrapper);
	
	List<CeshijingliView> selectListView(@Param("ew") Wrapper<CeshijingliEntity> wrapper);

	List<CeshijingliView> selectListView(Pagination page,@Param("ew") Wrapper<CeshijingliEntity> wrapper);

	
	CeshijingliView selectView(@Param("ew") Wrapper<CeshijingliEntity> wrapper);
	

}
