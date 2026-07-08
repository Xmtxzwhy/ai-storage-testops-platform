package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ZhixingrenwuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ZhixingrenwuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhixingrenwuView;


/**
 * 执行任务
 *
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public interface ZhixingrenwuService extends IService<ZhixingrenwuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZhixingrenwuVO> selectListVO(Wrapper<ZhixingrenwuEntity> wrapper);
   	
   	ZhixingrenwuVO selectVO(@Param("ew") Wrapper<ZhixingrenwuEntity> wrapper);
   	
   	List<ZhixingrenwuView> selectListView(Wrapper<ZhixingrenwuEntity> wrapper);
   	
   	ZhixingrenwuView selectView(@Param("ew") Wrapper<ZhixingrenwuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ZhixingrenwuEntity> wrapper);

   	

}

