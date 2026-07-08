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


import com.dao.CeshirenwuDao;
import com.entity.CeshirenwuEntity;
import com.service.CeshirenwuService;
import com.entity.vo.CeshirenwuVO;
import com.entity.view.CeshirenwuView;

@Service("ceshirenwuService")
public class CeshirenwuServiceImpl extends ServiceImpl<CeshirenwuDao, CeshirenwuEntity> implements CeshirenwuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CeshirenwuEntity> page = this.selectPage(
                new Query<CeshirenwuEntity>(params).getPage(),
                new EntityWrapper<CeshirenwuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<CeshirenwuEntity> wrapper) {
		  Page<CeshirenwuView> page =new Query<CeshirenwuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<CeshirenwuVO> selectListVO(Wrapper<CeshirenwuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public CeshirenwuVO selectVO(Wrapper<CeshirenwuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<CeshirenwuView> selectListView(Wrapper<CeshirenwuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public CeshirenwuView selectView(Wrapper<CeshirenwuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
