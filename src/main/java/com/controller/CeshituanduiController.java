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

import com.entity.CeshituanduiEntity;
import com.entity.view.CeshituanduiView;

import com.service.CeshituanduiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 测试团队
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
@RestController
@RequestMapping("/ceshituandui")
public class CeshituanduiController {
    @Autowired
    private CeshituanduiService ceshituanduiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,CeshituanduiEntity ceshituandui,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<CeshituanduiEntity> ew = new EntityWrapper<CeshituanduiEntity>();


        //查询结果
		PageUtils page = ceshituanduiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ceshituandui), params), params));
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
    public R list(@RequestParam Map<String, Object> params,CeshituanduiEntity ceshituandui, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<CeshituanduiEntity> ew = new EntityWrapper<CeshituanduiEntity>();

        //查询结果
		PageUtils page = ceshituanduiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ceshituandui), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( CeshituanduiEntity ceshituandui){
       	EntityWrapper<CeshituanduiEntity> ew = new EntityWrapper<CeshituanduiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( ceshituandui, "ceshituandui")); 
        return R.ok().put("data", ceshituanduiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(CeshituanduiEntity ceshituandui){
        EntityWrapper< CeshituanduiEntity> ew = new EntityWrapper< CeshituanduiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( ceshituandui, "ceshituandui")); 
		CeshituanduiView ceshituanduiView =  ceshituanduiService.selectView(ew);
		return R.ok("查询测试团队成功").put("data", ceshituanduiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CeshituanduiEntity ceshituandui = ceshituanduiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(ceshituandui,deSens);
        return R.ok().put("data", ceshituandui);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CeshituanduiEntity ceshituandui = ceshituanduiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(ceshituandui,deSens);
        return R.ok().put("data", ceshituandui);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CeshituanduiEntity ceshituandui, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ceshituandui);
        ceshituanduiService.insert(ceshituandui);
        return R.ok().put("data",ceshituandui.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody CeshituanduiEntity ceshituandui, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ceshituandui);
        ceshituanduiService.insert(ceshituandui);
        return R.ok().put("data",ceshituandui.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody CeshituanduiEntity ceshituandui, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ceshituandui);
        //全部更新
        ceshituanduiService.updateById(ceshituandui);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        ceshituanduiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
