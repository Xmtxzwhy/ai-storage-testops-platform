package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.CeshituanduiDao;
import com.entity.CeshituanduiEntity;
import com.service.CeshituanduiService;
import com.entity.vo.CeshituanduiVO;
import com.entity.view.CeshituanduiView;

@Service("ceshituanduiService")
public class CeshituanduiServiceImpl extends ServiceImpl<CeshituanduiDao, CeshituanduiEntity> implements CeshituanduiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CeshituanduiEntity> page = this.selectPage(
                new Query<CeshituanduiEntity>(params).getPage(),
                new EntityWrapper<CeshituanduiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<CeshituanduiEntity> wrapper) {
		  Page<CeshituanduiView> page =new Query<CeshituanduiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<CeshituanduiVO> selectListVO(Wrapper<CeshituanduiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public CeshituanduiVO selectVO(Wrapper<CeshituanduiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<CeshituanduiView> selectListView(Wrapper<CeshituanduiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public CeshituanduiView selectView(Wrapper<CeshituanduiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
