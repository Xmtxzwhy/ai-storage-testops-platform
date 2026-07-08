package com.dao;

import com.entity.ZhixingrenwuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ZhixingrenwuVO;
import com.entity.view.ZhixingrenwuView;


/**
 * 执行任务
 * 
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public interface ZhixingrenwuDao extends BaseMapper<ZhixingrenwuEntity> {
	
	List<ZhixingrenwuVO> selectListVO(@Param("ew") Wrapper<ZhixingrenwuEntity> wrapper);
	
	ZhixingrenwuVO selectVO(@Param("ew") Wrapper<ZhixingrenwuEntity> wrapper);
	
	List<ZhixingrenwuView> selectListView(@Param("ew") Wrapper<ZhixingrenwuEntity> wrapper);

	List<ZhixingrenwuView> selectListView(Pagination page,@Param("ew") Wrapper<ZhixingrenwuEntity> wrapper);

	
	ZhixingrenwuView selectView(@Param("ew") Wrapper<ZhixingrenwuEntity> wrapper);
	

}
