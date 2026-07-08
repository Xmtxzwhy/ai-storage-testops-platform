package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.CeshirenwuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.CeshirenwuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.CeshirenwuView;


/**
 * 测试任务
 *
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public interface CeshirenwuService extends IService<CeshirenwuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<CeshirenwuVO> selectListVO(Wrapper<CeshirenwuEntity> wrapper);
   	
   	CeshirenwuVO selectVO(@Param("ew") Wrapper<CeshirenwuEntity> wrapper);
   	
   	List<CeshirenwuView> selectListView(Wrapper<CeshirenwuEntity> wrapper);
   	
   	CeshirenwuView selectView(@Param("ew") Wrapper<CeshirenwuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<CeshirenwuEntity> wrapper);

   	

}

