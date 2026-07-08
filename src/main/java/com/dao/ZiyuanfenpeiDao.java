package com.dao;

import com.entity.ZiyuanfenpeiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ZiyuanfenpeiVO;
import com.entity.view.ZiyuanfenpeiView;


/**
 * 资源分配
 * 
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public interface ZiyuanfenpeiDao extends BaseMapper<ZiyuanfenpeiEntity> {
	
	List<ZiyuanfenpeiVO> selectListVO(@Param("ew") Wrapper<ZiyuanfenpeiEntity> wrapper);
	
	ZiyuanfenpeiVO selectVO(@Param("ew") Wrapper<ZiyuanfenpeiEntity> wrapper);
	
	List<ZiyuanfenpeiView> selectListView(@Param("ew") Wrapper<ZiyuanfenpeiEntity> wrapper);

	List<ZiyuanfenpeiView> selectListView(Pagination page,@Param("ew") Wrapper<ZiyuanfenpeiEntity> wrapper);

	
	ZiyuanfenpeiView selectView(@Param("ew") Wrapper<ZiyuanfenpeiEntity> wrapper);
	

}
