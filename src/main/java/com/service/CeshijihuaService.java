package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.CeshijihuaEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.CeshijihuaVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.CeshijihuaView;


/**
 * 测试计划
 *
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public interface CeshijihuaService extends IService<CeshijihuaEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<CeshijihuaVO> selectListVO(Wrapper<CeshijihuaEntity> wrapper);
   	
   	CeshijihuaVO selectVO(@Param("ew") Wrapper<CeshijihuaEntity> wrapper);
   	
   	List<CeshijihuaView> selectListView(Wrapper<CeshijihuaEntity> wrapper);
   	
   	CeshijihuaView selectView(@Param("ew") Wrapper<CeshijihuaEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<CeshijihuaEntity> wrapper);

   	

}

