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

import com.entity.CeshihuodongEntity;
import com.entity.view.CeshihuodongView;

import com.service.CeshihuodongService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 测试活动
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
@RestController
@RequestMapping("/ceshihuodong")
public class CeshihuodongController {
    @Autowired
    private CeshihuodongService ceshihuodongService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,CeshihuodongEntity ceshihuodong,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("ceshijingli")) {
			ceshihuodong.setJinglizhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<CeshihuodongEntity> ew = new EntityWrapper<CeshihuodongEntity>();


        //查询结果
		PageUtils page = ceshihuodongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ceshihuodong), params), params));
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
    public R list(@RequestParam Map<String, Object> params,CeshihuodongEntity ceshihuodong, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<CeshihuodongEntity> ew = new EntityWrapper<CeshihuodongEntity>();

        //查询结果
		PageUtils page = ceshihuodongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ceshihuodong), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( CeshihuodongEntity ceshihuodong){
       	EntityWrapper<CeshihuodongEntity> ew = new EntityWrapper<CeshihuodongEntity>();
      	ew.allEq(MPUtil.allEQMapPre( ceshihuodong, "ceshihuodong")); 
        return R.ok().put("data", ceshihuodongService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(CeshihuodongEntity ceshihuodong){
        EntityWrapper< CeshihuodongEntity> ew = new EntityWrapper< CeshihuodongEntity>();
 		ew.allEq(MPUtil.allEQMapPre( ceshihuodong, "ceshihuodong")); 
		CeshihuodongView ceshihuodongView =  ceshihuodongService.selectView(ew);
		return R.ok("查询测试活动成功").put("data", ceshihuodongView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CeshihuodongEntity ceshihuodong = ceshihuodongService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(ceshihuodong,deSens);
        return R.ok().put("data", ceshihuodong);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CeshihuodongEntity ceshihuodong = ceshihuodongService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(ceshihuodong,deSens);
        return R.ok().put("data", ceshihuodong);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CeshihuodongEntity ceshihuodong, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ceshihuodong);
        ceshihuodongService.insert(ceshihuodong);
        return R.ok().put("data",ceshihuodong.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody CeshihuodongEntity ceshihuodong, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ceshihuodong);
        ceshihuodongService.insert(ceshihuodong);
        return R.ok().put("data",ceshihuodong.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody CeshihuodongEntity ceshihuodong, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ceshihuodong);
        //全部更新
        ceshihuodongService.updateById(ceshihuodong);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        ceshihuodongService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
