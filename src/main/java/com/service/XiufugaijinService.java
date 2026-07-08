package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.XiufugaijinEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.XiufugaijinVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.XiufugaijinView;


/**
 * 修复改进
 *
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public interface XiufugaijinService extends IService<XiufugaijinEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<XiufugaijinVO> selectListVO(Wrapper<XiufugaijinEntity> wrapper);
   	
   	XiufugaijinVO selectVO(@Param("ew") Wrapper<XiufugaijinEntity> wrapper);
   	
   	List<XiufugaijinView> selectListView(Wrapper<XiufugaijinEntity> wrapper);
   	
   	XiufugaijinView selectView(@Param("ew") Wrapper<XiufugaijinEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<XiufugaijinEntity> wrapper);

   	

}

