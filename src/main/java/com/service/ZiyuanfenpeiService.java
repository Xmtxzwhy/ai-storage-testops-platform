package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ZiyuanfenpeiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ZiyuanfenpeiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ZiyuanfenpeiView;


/**
 * 资源分配
 *
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public interface ZiyuanfenpeiService extends IService<ZiyuanfenpeiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZiyuanfenpeiVO> selectListVO(Wrapper<ZiyuanfenpeiEntity> wrapper);
   	
   	ZiyuanfenpeiVO selectVO(@Param("ew") Wrapper<ZiyuanfenpeiEntity> wrapper);
   	
   	List<ZiyuanfenpeiView> selectListView(Wrapper<ZiyuanfenpeiEntity> wrapper);
   	
   	ZiyuanfenpeiView selectView(@Param("ew") Wrapper<ZiyuanfenpeiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ZiyuanfenpeiEntity> wrapper);

   	

}

