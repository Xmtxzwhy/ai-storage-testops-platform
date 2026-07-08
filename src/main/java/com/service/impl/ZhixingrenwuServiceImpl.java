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


import com.dao.ZhixingrenwuDao;
import com.entity.ZhixingrenwuEntity;
import com.service.ZhixingrenwuService;
import com.entity.vo.ZhixingrenwuVO;
import com.entity.view.ZhixingrenwuView;

@Service("zhixingrenwuService")
public class ZhixingrenwuServiceImpl extends ServiceImpl<ZhixingrenwuDao, ZhixingrenwuEntity> implements ZhixingrenwuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZhixingrenwuEntity> page = this.selectPage(
                new Query<ZhixingrenwuEntity>(params).getPage(),
                new EntityWrapper<ZhixingrenwuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ZhixingrenwuEntity> wrapper) {
		  Page<ZhixingrenwuView> page =new Query<ZhixingrenwuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ZhixingrenwuVO> selectListVO(Wrapper<ZhixingrenwuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ZhixingrenwuVO selectVO(Wrapper<ZhixingrenwuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ZhixingrenwuView> selectListView(Wrapper<ZhixingrenwuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ZhixingrenwuView selectView(Wrapper<ZhixingrenwuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
