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


import com.dao.FenxishiDao;
import com.entity.FenxishiEntity;
import com.service.FenxishiService;
import com.entity.vo.FenxishiVO;
import com.entity.view.FenxishiView;

@Service("fenxishiService")
public class FenxishiServiceImpl extends ServiceImpl<FenxishiDao, FenxishiEntity> implements FenxishiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<FenxishiEntity> page = this.selectPage(
                new Query<FenxishiEntity>(params).getPage(),
                new EntityWrapper<FenxishiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<FenxishiEntity> wrapper) {
		  Page<FenxishiView> page =new Query<FenxishiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<FenxishiVO> selectListVO(Wrapper<FenxishiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public FenxishiVO selectVO(Wrapper<FenxishiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<FenxishiView> selectListView(Wrapper<FenxishiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public FenxishiView selectView(Wrapper<FenxishiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
