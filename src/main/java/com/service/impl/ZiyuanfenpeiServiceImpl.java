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


import com.dao.ZiyuanfenpeiDao;
import com.entity.ZiyuanfenpeiEntity;
import com.service.ZiyuanfenpeiService;
import com.entity.vo.ZiyuanfenpeiVO;
import com.entity.view.ZiyuanfenpeiView;

@Service("ziyuanfenpeiService")
public class ZiyuanfenpeiServiceImpl extends ServiceImpl<ZiyuanfenpeiDao, ZiyuanfenpeiEntity> implements ZiyuanfenpeiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZiyuanfenpeiEntity> page = this.selectPage(
                new Query<ZiyuanfenpeiEntity>(params).getPage(),
                new EntityWrapper<ZiyuanfenpeiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ZiyuanfenpeiEntity> wrapper) {
		  Page<ZiyuanfenpeiView> page =new Query<ZiyuanfenpeiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ZiyuanfenpeiVO> selectListVO(Wrapper<ZiyuanfenpeiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ZiyuanfenpeiVO selectVO(Wrapper<ZiyuanfenpeiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ZiyuanfenpeiView> selectListView(Wrapper<ZiyuanfenpeiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ZiyuanfenpeiView selectView(Wrapper<ZiyuanfenpeiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
