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


import com.dao.XiufugaijinDao;
import com.entity.XiufugaijinEntity;
import com.service.XiufugaijinService;
import com.entity.vo.XiufugaijinVO;
import com.entity.view.XiufugaijinView;

@Service("xiufugaijinService")
public class XiufugaijinServiceImpl extends ServiceImpl<XiufugaijinDao, XiufugaijinEntity> implements XiufugaijinService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<XiufugaijinEntity> page = this.selectPage(
                new Query<XiufugaijinEntity>(params).getPage(),
                new EntityWrapper<XiufugaijinEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<XiufugaijinEntity> wrapper) {
		  Page<XiufugaijinView> page =new Query<XiufugaijinView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<XiufugaijinVO> selectListVO(Wrapper<XiufugaijinEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public XiufugaijinVO selectVO(Wrapper<XiufugaijinEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<XiufugaijinView> selectListView(Wrapper<XiufugaijinEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public XiufugaijinView selectView(Wrapper<XiufugaijinEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
