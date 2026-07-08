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


import com.dao.CeshijingliDao;
import com.entity.CeshijingliEntity;
import com.service.CeshijingliService;
import com.entity.vo.CeshijingliVO;
import com.entity.view.CeshijingliView;

@Service("ceshijingliService")
public class CeshijingliServiceImpl extends ServiceImpl<CeshijingliDao, CeshijingliEntity> implements CeshijingliService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CeshijingliEntity> page = this.selectPage(
                new Query<CeshijingliEntity>(params).getPage(),
                new EntityWrapper<CeshijingliEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<CeshijingliEntity> wrapper) {
		  Page<CeshijingliView> page =new Query<CeshijingliView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<CeshijingliVO> selectListVO(Wrapper<CeshijingliEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public CeshijingliVO selectVO(Wrapper<CeshijingliEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<CeshijingliView> selectListView(Wrapper<CeshijingliEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public CeshijingliView selectView(Wrapper<CeshijingliEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
