package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.CeshijingliEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.CeshijingliVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.CeshijingliView;


/**
 * 测试经理
 *
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public interface CeshijingliService extends IService<CeshijingliEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<CeshijingliVO> selectListVO(Wrapper<CeshijingliEntity> wrapper);
   	
   	CeshijingliVO selectVO(@Param("ew") Wrapper<CeshijingliEntity> wrapper);
   	
   	List<CeshijingliView> selectListView(Wrapper<CeshijingliEntity> wrapper);
   	
   	CeshijingliView selectView(@Param("ew") Wrapper<CeshijingliEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<CeshijingliEntity> wrapper);

   	

}

