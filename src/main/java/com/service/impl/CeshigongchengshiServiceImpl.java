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


import com.dao.CeshigongchengshiDao;
import com.entity.CeshigongchengshiEntity;
import com.service.CeshigongchengshiService;
import com.entity.vo.CeshigongchengshiVO;
import com.entity.view.CeshigongchengshiView;

@Service("ceshigongchengshiService")
public class CeshigongchengshiServiceImpl extends ServiceImpl<CeshigongchengshiDao, CeshigongchengshiEntity> implements CeshigongchengshiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CeshigongchengshiEntity> page = this.selectPage(
                new Query<CeshigongchengshiEntity>(params).getPage(),
                new EntityWrapper<CeshigongchengshiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<CeshigongchengshiEntity> wrapper) {
		  Page<CeshigongchengshiView> page =new Query<CeshigongchengshiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<CeshigongchengshiVO> selectListVO(Wrapper<CeshigongchengshiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public CeshigongchengshiVO selectVO(Wrapper<CeshigongchengshiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<CeshigongchengshiView> selectListView(Wrapper<CeshigongchengshiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public CeshigongchengshiView selectView(Wrapper<CeshigongchengshiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
