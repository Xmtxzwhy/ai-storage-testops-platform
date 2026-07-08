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


import com.dao.CeshijihuaDao;
import com.entity.CeshijihuaEntity;
import com.service.CeshijihuaService;
import com.entity.vo.CeshijihuaVO;
import com.entity.view.CeshijihuaView;

@Service("ceshijihuaService")
public class CeshijihuaServiceImpl extends ServiceImpl<CeshijihuaDao, CeshijihuaEntity> implements CeshijihuaService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CeshijihuaEntity> page = this.selectPage(
                new Query<CeshijihuaEntity>(params).getPage(),
                new EntityWrapper<CeshijihuaEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<CeshijihuaEntity> wrapper) {
		  Page<CeshijihuaView> page =new Query<CeshijihuaView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<CeshijihuaVO> selectListVO(Wrapper<CeshijihuaEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public CeshijihuaVO selectVO(Wrapper<CeshijihuaEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<CeshijihuaView> selectListView(Wrapper<CeshijihuaEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public CeshijihuaView selectView(Wrapper<CeshijihuaEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
