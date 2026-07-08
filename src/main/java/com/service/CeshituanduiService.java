package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.CeshituanduiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.CeshituanduiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.CeshituanduiView;


/**
 * 测试团队
 *
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public interface CeshituanduiService extends IService<CeshituanduiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<CeshituanduiVO> selectListVO(Wrapper<CeshituanduiEntity> wrapper);
   	
   	CeshituanduiVO selectVO(@Param("ew") Wrapper<CeshituanduiEntity> wrapper);
   	
   	List<CeshituanduiView> selectListView(Wrapper<CeshituanduiEntity> wrapper);
   	
   	CeshituanduiView selectView(@Param("ew") Wrapper<CeshituanduiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<CeshituanduiEntity> wrapper);

   	

}

