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


import com.dao.CeshifenxiDao;
import com.entity.CeshifenxiEntity;
import com.service.CeshifenxiService;
import com.entity.vo.CeshifenxiVO;
import com.entity.view.CeshifenxiView;

@Service("ceshifenxiService")
public class CeshifenxiServiceImpl extends ServiceImpl<CeshifenxiDao, CeshifenxiEntity> implements CeshifenxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CeshifenxiEntity> page = this.selectPage(
                new Query<CeshifenxiEntity>(params).getPage(),
                new EntityWrapper<CeshifenxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<CeshifenxiEntity> wrapper) {
		  Page<CeshifenxiView> page =new Query<CeshifenxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<CeshifenxiVO> selectListVO(Wrapper<CeshifenxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public CeshifenxiVO selectVO(Wrapper<CeshifenxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<CeshifenxiView> selectListView(Wrapper<CeshifenxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public CeshifenxiView selectView(Wrapper<CeshifenxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<CeshifenxiEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<CeshifenxiEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<CeshifenxiEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }


    @Override
    public List<Map<String, Object>> renwumingchengceshijieguoTypeStat(Map<String, Object> params, Wrapper<CeshifenxiEntity> wrapper) {
        return baseMapper.renwumingchengceshijieguoTypeStat(params, wrapper);
    }


}
