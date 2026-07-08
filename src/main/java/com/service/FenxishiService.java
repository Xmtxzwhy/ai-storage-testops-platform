package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.FenxishiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.FenxishiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.FenxishiView;


/**
 * QA分析师
 *
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public interface FenxishiService extends IService<FenxishiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<FenxishiVO> selectListVO(Wrapper<FenxishiEntity> wrapper);
   	
   	FenxishiVO selectVO(@Param("ew") Wrapper<FenxishiEntity> wrapper);
   	
   	List<FenxishiView> selectListView(Wrapper<FenxishiEntity> wrapper);
   	
   	FenxishiView selectView(@Param("ew") Wrapper<FenxishiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<FenxishiEntity> wrapper);

   	

}

