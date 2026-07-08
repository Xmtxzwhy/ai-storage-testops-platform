package com.dao;

import com.entity.XiangmujingliEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.XiangmujingliVO;
import com.entity.view.XiangmujingliView;


/**
 * 项目经理
 * 
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public interface XiangmujingliDao extends BaseMapper<XiangmujingliEntity> {
	
	List<XiangmujingliVO> selectListVO(@Param("ew") Wrapper<XiangmujingliEntity> wrapper);
	
	XiangmujingliVO selectVO(@Param("ew") Wrapper<XiangmujingliEntity> wrapper);
	
	List<XiangmujingliView> selectListView(@Param("ew") Wrapper<XiangmujingliEntity> wrapper);

	List<XiangmujingliView> selectListView(Pagination page,@Param("ew") Wrapper<XiangmujingliEntity> wrapper);

	
	XiangmujingliView selectView(@Param("ew") Wrapper<XiangmujingliEntity> wrapper);
	

}
