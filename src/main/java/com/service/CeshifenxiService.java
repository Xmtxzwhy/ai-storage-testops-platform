package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.CeshifenxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.CeshifenxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.CeshifenxiView;


/**
 * 测试分析
 *
 * @author 
 * @email 
 * @date 2025-03-03 22:28:05
 */
public interface CeshifenxiService extends IService<CeshifenxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<CeshifenxiVO> selectListVO(Wrapper<CeshifenxiEntity> wrapper);
   	
   	CeshifenxiVO selectVO(@Param("ew") Wrapper<CeshifenxiEntity> wrapper);
   	
   	List<CeshifenxiView> selectListView(Wrapper<CeshifenxiEntity> wrapper);
   	
   	CeshifenxiView selectView(@Param("ew") Wrapper<CeshifenxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<CeshifenxiEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<CeshifenxiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<CeshifenxiEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<CeshifenxiEntity> wrapper);


    List<Map<String, Object>> renwumingchengceshijieguoTypeStat(Map<String, Object> params,Wrapper<CeshifenxiEntity> wrapper);

}

