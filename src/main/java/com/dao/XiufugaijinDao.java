package com.dao;

import com.entity.XiufugaijinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.XiufugaijinVO;
import com.entity.view.XiufugaijinView;


/**
 * 修复改进
 * 
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public interface XiufugaijinDao extends BaseMapper<XiufugaijinEntity> {
	
	List<XiufugaijinVO> selectListVO(@Param("ew") Wrapper<XiufugaijinEntity> wrapper);
	
	XiufugaijinVO selectVO(@Param("ew") Wrapper<XiufugaijinEntity> wrapper);
	
	List<XiufugaijinView> selectListView(@Param("ew") Wrapper<XiufugaijinEntity> wrapper);

	List<XiufugaijinView> selectListView(Pagination page,@Param("ew") Wrapper<XiufugaijinEntity> wrapper);

	
	XiufugaijinView selectView(@Param("ew") Wrapper<XiufugaijinEntity> wrapper);
	

}
