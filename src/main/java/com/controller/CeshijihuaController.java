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

import com.entity.CeshijihuaEntity;
import com.entity.view.CeshijihuaView;

import com.service.CeshijihuaService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 测试计划
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
@RestController
@RequestMapping("/ceshijihua")
public class CeshijihuaController {
    @Autowired
    private CeshijihuaService ceshijihuaService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,CeshijihuaEntity ceshijihua,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("ceshijingli")) {
			ceshijihua.setJinglizhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<CeshijihuaEntity> ew = new EntityWrapper<CeshijihuaEntity>();


        //查询结果
		PageUtils page = ceshijihuaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ceshijihua), params), params));
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
    public R list(@RequestParam Map<String, Object> params,CeshijihuaEntity ceshijihua, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<CeshijihuaEntity> ew = new EntityWrapper<CeshijihuaEntity>();

        //查询结果
		PageUtils page = ceshijihuaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ceshijihua), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( CeshijihuaEntity ceshijihua){
       	EntityWrapper<CeshijihuaEntity> ew = new EntityWrapper<CeshijihuaEntity>();
      	ew.allEq(MPUtil.allEQMapPre( ceshijihua, "ceshijihua")); 
        return R.ok().put("data", ceshijihuaService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(CeshijihuaEntity ceshijihua){
        EntityWrapper< CeshijihuaEntity> ew = new EntityWrapper< CeshijihuaEntity>();
 		ew.allEq(MPUtil.allEQMapPre( ceshijihua, "ceshijihua")); 
		CeshijihuaView ceshijihuaView =  ceshijihuaService.selectView(ew);
		return R.ok("查询测试计划成功").put("data", ceshijihuaView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CeshijihuaEntity ceshijihua = ceshijihuaService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(ceshijihua,deSens);
        return R.ok().put("data", ceshijihua);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CeshijihuaEntity ceshijihua = ceshijihuaService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(ceshijihua,deSens);
        return R.ok().put("data", ceshijihua);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CeshijihuaEntity ceshijihua, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ceshijihua);
        ceshijihuaService.insert(ceshijihua);
        return R.ok().put("data",ceshijihua.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody CeshijihuaEntity ceshijihua, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ceshijihua);
        ceshijihuaService.insert(ceshijihua);
        return R.ok().put("data",ceshijihua.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody CeshijihuaEntity ceshijihua, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ceshijihua);
        //全部更新
        ceshijihuaService.updateById(ceshijihua);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        ceshijihuaService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
