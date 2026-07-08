package com.dao;

import com.entity.CeshiyongliEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.CeshiyongliVO;
import com.entity.view.CeshiyongliView;


/**
 * 测试用例
 * 
 * @author 
 * @email 
 * @date 2025-03-03 22:28:05
 */
public interface CeshiyongliDao extends BaseMapper<CeshiyongliEntity> {
	
	List<CeshiyongliVO> selectListVO(@Param("ew") Wrapper<CeshiyongliEntity> wrapper);
	
	CeshiyongliVO selectVO(@Param("ew") Wrapper<CeshiyongliEntity> wrapper);
	
	List<CeshiyongliView> selectListView(@Param("ew") Wrapper<CeshiyongliEntity> wrapper);

	List<CeshiyongliView> selectListView(Pagination page,@Param("ew") Wrapper<CeshiyongliEntity> wrapper);

	
	CeshiyongliView selectView(@Param("ew") Wrapper<CeshiyongliEntity> wrapper);
	

}
