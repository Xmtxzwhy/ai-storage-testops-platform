package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.XiangmuxinxiEntity;
import com.entity.view.XiangmuxinxiView;

import com.service.XiangmuxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 项目信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
@RestController
@RequestMapping("/xiangmuxinxi")
public class XiangmuxinxiController {
    @Autowired
    private XiangmuxinxiService xiangmuxinxiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XiangmuxinxiEntity xiangmuxinxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("xiangmujingli")) {
			xiangmuxinxi.setJingligonghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<XiangmuxinxiEntity> ew = new EntityWrapper<XiangmuxinxiEntity>();


        //查询结果
		PageUtils page = xiangmuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xiangmuxinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,XiangmuxinxiEntity xiangmuxinxi, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<XiangmuxinxiEntity> ew = new EntityWrapper<XiangmuxinxiEntity>();

        //查询结果
		PageUtils page = xiangmuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xiangmuxinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XiangmuxinxiEntity xiangmuxinxi){
       	EntityWrapper<XiangmuxinxiEntity> ew = new EntityWrapper<XiangmuxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xiangmuxinxi, "xiangmuxinxi")); 
        return R.ok().put("data", xiangmuxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XiangmuxinxiEntity xiangmuxinxi){
        EntityWrapper< XiangmuxinxiEntity> ew = new EntityWrapper< XiangmuxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xiangmuxinxi, "xiangmuxinxi")); 
		XiangmuxinxiView xiangmuxinxiView =  xiangmuxinxiService.selectView(ew);
		return R.ok("查询项目信息成功").put("data", xiangmuxinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XiangmuxinxiEntity xiangmuxinxi = xiangmuxinxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xiangmuxinxi,deSens);
        return R.ok().put("data", xiangmuxinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XiangmuxinxiEntity xiangmuxinxi = xiangmuxinxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xiangmuxinxi,deSens);
        return R.ok().put("data", xiangmuxinxi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody XiangmuxinxiEntity xiangmuxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xiangmuxinxi);
        xiangmuxinxiService.insert(xiangmuxinxi);
        return R.ok().put("data",xiangmuxinxi.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody XiangmuxinxiEntity xiangmuxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xiangmuxinxi);
        xiangmuxinxiService.insert(xiangmuxinxi);
        return R.ok().put("data",xiangmuxinxi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody XiangmuxinxiEntity xiangmuxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xiangmuxinxi);
        //全部更新
        xiangmuxinxiService.updateById(xiangmuxinxi);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        xiangmuxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
