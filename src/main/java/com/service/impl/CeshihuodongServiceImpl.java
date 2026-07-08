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


import com.dao.CeshihuodongDao;
import com.entity.CeshihuodongEntity;
import com.service.CeshihuodongService;
import com.entity.vo.CeshihuodongVO;
import com.entity.view.CeshihuodongView;

@Service("ceshihuodongService")
public class CeshihuodongServiceImpl extends ServiceImpl<CeshihuodongDao, CeshihuodongEntity> implements CeshihuodongService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CeshihuodongEntity> page = this.selectPage(
                new Query<CeshihuodongEntity>(params).getPage(),
                new EntityWrapper<CeshihuodongEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<CeshihuodongEntity> wrapper) {
		  Page<CeshihuodongView> page =new Query<CeshihuodongView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<CeshihuodongVO> selectListVO(Wrapper<CeshihuodongEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public CeshihuodongVO selectVO(Wrapper<CeshihuodongEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<CeshihuodongView> selectListView(Wrapper<CeshihuodongEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public CeshihuodongView selectView(Wrapper<CeshihuodongEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
