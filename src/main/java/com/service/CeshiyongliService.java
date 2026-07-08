package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.CeshiyongliEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.CeshiyongliVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.CeshiyongliView;


/**
 * 测试用例
 *
 * @author 
 * @email 
 * @date 2025-03-03 22:28:05
 */
public interface CeshiyongliService extends IService<CeshiyongliEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<CeshiyongliVO> selectListVO(Wrapper<CeshiyongliEntity> wrapper);
   	
   	CeshiyongliVO selectVO(@Param("ew") Wrapper<CeshiyongliEntity> wrapper);
   	
   	List<CeshiyongliView> selectListView(Wrapper<CeshiyongliEntity> wrapper);
   	
   	CeshiyongliView selectView(@Param("ew") Wrapper<CeshiyongliEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<CeshiyongliEntity> wrapper);

   	

}

