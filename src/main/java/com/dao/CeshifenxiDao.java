package com.dao;

import com.entity.CeshifenxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.CeshifenxiVO;
import com.entity.view.CeshifenxiView;


/**
 * 测试分析
 * 
 * @author 
 * @email 
 * @date 2025-03-03 22:28:05
 */
public interface CeshifenxiDao extends BaseMapper<CeshifenxiEntity> {
	
	List<CeshifenxiVO> selectListVO(@Param("ew") Wrapper<CeshifenxiEntity> wrapper);
	
	CeshifenxiVO selectVO(@Param("ew") Wrapper<CeshifenxiEntity> wrapper);
	
	List<CeshifenxiView> selectListView(@Param("ew") Wrapper<CeshifenxiEntity> wrapper);

	List<CeshifenxiView> selectListView(Pagination page,@Param("ew") Wrapper<CeshifenxiEntity> wrapper);

	
	CeshifenxiView selectView(@Param("ew") Wrapper<CeshifenxiEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<CeshifenxiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<CeshifenxiEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<CeshifenxiEntity> wrapper);


    List<Map<String, Object>> renwumingchengceshijieguoTypeStat(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<CeshifenxiEntity> wrapper);

}
