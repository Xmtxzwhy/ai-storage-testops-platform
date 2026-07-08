package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.CeshihuodongEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.CeshihuodongVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.CeshihuodongView;


/**
 * 测试活动
 *
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public interface CeshihuodongService extends IService<CeshihuodongEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<CeshihuodongVO> selectListVO(Wrapper<CeshihuodongEntity> wrapper);
   	
   	CeshihuodongVO selectVO(@Param("ew") Wrapper<CeshihuodongEntity> wrapper);
   	
   	List<CeshihuodongView> selectListView(Wrapper<CeshihuodongEntity> wrapper);
   	
   	CeshihuodongView selectView(@Param("ew") Wrapper<CeshihuodongEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<CeshihuodongEntity> wrapper);

   	

}

