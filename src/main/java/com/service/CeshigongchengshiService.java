package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.CeshigongchengshiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.CeshigongchengshiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.CeshigongchengshiView;


/**
 * 测试工程师
 *
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public interface CeshigongchengshiService extends IService<CeshigongchengshiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<CeshigongchengshiVO> selectListVO(Wrapper<CeshigongchengshiEntity> wrapper);
   	
   	CeshigongchengshiVO selectVO(@Param("ew") Wrapper<CeshigongchengshiEntity> wrapper);
   	
   	List<CeshigongchengshiView> selectListView(Wrapper<CeshigongchengshiEntity> wrapper);
   	
   	CeshigongchengshiView selectView(@Param("ew") Wrapper<CeshigongchengshiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<CeshigongchengshiEntity> wrapper);

   	

}

