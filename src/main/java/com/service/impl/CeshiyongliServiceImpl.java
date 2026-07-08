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


import com.dao.CeshiyongliDao;
import com.entity.CeshiyongliEntity;
import com.service.CeshiyongliService;
import com.entity.vo.CeshiyongliVO;
import com.entity.view.CeshiyongliView;

@Service("ceshiyongliService")
public class CeshiyongliServiceImpl extends ServiceImpl<CeshiyongliDao, CeshiyongliEntity> implements CeshiyongliService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CeshiyongliEntity> page = this.selectPage(
                new Query<CeshiyongliEntity>(params).getPage(),
                new EntityWrapper<CeshiyongliEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<CeshiyongliEntity> wrapper) {
		  Page<CeshiyongliView> page =new Query<CeshiyongliView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<CeshiyongliVO> selectListVO(Wrapper<CeshiyongliEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public CeshiyongliVO selectVO(Wrapper<CeshiyongliEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<CeshiyongliView> selectListView(Wrapper<CeshiyongliEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public CeshiyongliView selectView(Wrapper<CeshiyongliEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
