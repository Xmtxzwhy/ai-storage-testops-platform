package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.XiangmujingliEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.XiangmujingliVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.XiangmujingliView;


/**
 * 项目经理
 *
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public interface XiangmujingliService extends IService<XiangmujingliEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XiangmujingliVO> selectListVO(Wrapper<XiangmujingliEntity> wrapper);
   	
   	XiangmujingliVO selectVO(@Param("ew") Wrapper<XiangmujingliEntity> wrapper);
   	
   	List<XiangmujingliView> selectListView(Wrapper<XiangmujingliEntity> wrapper);
   	
   	XiangmujingliView selectView(@Param("ew") Wrapper<XiangmujingliEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<XiangmujingliEntity> wrapper);

   	

}

