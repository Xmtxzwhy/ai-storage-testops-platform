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

import com.entity.CeshiyongliEntity;
import com.entity.view.CeshiyongliView;

import com.service.CeshiyongliService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 测试用例
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-03 22:28:05
 */
@RestController
@RequestMapping("/ceshiyongli")
public class CeshiyongliController {
    @Autowired
    private CeshiyongliService ceshiyongliService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,CeshiyongliEntity ceshiyongli,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("ceshijingli")) {
			ceshiyongli.setJinglizhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<CeshiyongliEntity> ew = new EntityWrapper<CeshiyongliEntity>();


        //查询结果
		PageUtils page = ceshiyongliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ceshiyongli), params), params));
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
    public R list(@RequestParam Map<String, Object> params,CeshiyongliEntity ceshiyongli, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<CeshiyongliEntity> ew = new EntityWrapper<CeshiyongliEntity>();

        //查询结果
		PageUtils page = ceshiyongliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ceshiyongli), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( CeshiyongliEntity ceshiyongli){
       	EntityWrapper<CeshiyongliEntity> ew = new EntityWrapper<CeshiyongliEntity>();
      	ew.allEq(MPUtil.allEQMapPre( ceshiyongli, "ceshiyongli")); 
        return R.ok().put("data", ceshiyongliService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(CeshiyongliEntity ceshiyongli){
        EntityWrapper< CeshiyongliEntity> ew = new EntityWrapper< CeshiyongliEntity>();
 		ew.allEq(MPUtil.allEQMapPre( ceshiyongli, "ceshiyongli")); 
		CeshiyongliView ceshiyongliView =  ceshiyongliService.selectView(ew);
		return R.ok("查询测试用例成功").put("data", ceshiyongliView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CeshiyongliEntity ceshiyongli = ceshiyongliService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(ceshiyongli,deSens);
        return R.ok().put("data", ceshiyongli);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CeshiyongliEntity ceshiyongli = ceshiyongliService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(ceshiyongli,deSens);
        return R.ok().put("data", ceshiyongli);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CeshiyongliEntity ceshiyongli, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ceshiyongli);
        ceshiyongliService.insert(ceshiyongli);
        return R.ok().put("data",ceshiyongli.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody CeshiyongliEntity ceshiyongli, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ceshiyongli);
        ceshiyongliService.insert(ceshiyongli);
        return R.ok().put("data",ceshiyongli.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody CeshiyongliEntity ceshiyongli, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ceshiyongli);
        //全部更新
        ceshiyongliService.updateById(ceshiyongli);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        ceshiyongliService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
