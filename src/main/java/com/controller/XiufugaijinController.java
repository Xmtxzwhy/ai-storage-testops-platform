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

import com.entity.XiufugaijinEntity;
import com.entity.view.XiufugaijinView;

import com.service.XiufugaijinService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 修复改进
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
@RestController
@RequestMapping("/xiufugaijin")
public class XiufugaijinController {
    @Autowired
    private XiufugaijinService xiufugaijinService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XiufugaijinEntity xiufugaijin,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("ceshijingli")) {
			xiufugaijin.setJinglizhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("ceshigongchengshi")) {
			xiufugaijin.setGongchengshihao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("kaifarenyuan")) {
			xiufugaijin.setKaifazhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<XiufugaijinEntity> ew = new EntityWrapper<XiufugaijinEntity>();


        //查询结果
		PageUtils page = xiufugaijinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xiufugaijin), params), params));
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
    public R list(@RequestParam Map<String, Object> params,XiufugaijinEntity xiufugaijin, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<XiufugaijinEntity> ew = new EntityWrapper<XiufugaijinEntity>();

        //查询结果
		PageUtils page = xiufugaijinService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xiufugaijin), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XiufugaijinEntity xiufugaijin){
       	EntityWrapper<XiufugaijinEntity> ew = new EntityWrapper<XiufugaijinEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xiufugaijin, "xiufugaijin")); 
        return R.ok().put("data", xiufugaijinService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XiufugaijinEntity xiufugaijin){
        EntityWrapper< XiufugaijinEntity> ew = new EntityWrapper< XiufugaijinEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xiufugaijin, "xiufugaijin")); 
		XiufugaijinView xiufugaijinView =  xiufugaijinService.selectView(ew);
		return R.ok("查询修复改进成功").put("data", xiufugaijinView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XiufugaijinEntity xiufugaijin = xiufugaijinService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xiufugaijin,deSens);
        return R.ok().put("data", xiufugaijin);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XiufugaijinEntity xiufugaijin = xiufugaijinService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xiufugaijin,deSens);
        return R.ok().put("data", xiufugaijin);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody XiufugaijinEntity xiufugaijin, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xiufugaijin);
        xiufugaijinService.insert(xiufugaijin);
        return R.ok().put("data",xiufugaijin.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody XiufugaijinEntity xiufugaijin, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xiufugaijin);
        xiufugaijinService.insert(xiufugaijin);
        return R.ok().put("data",xiufugaijin.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody XiufugaijinEntity xiufugaijin, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xiufugaijin);
        //全部更新
        xiufugaijinService.updateById(xiufugaijin);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        xiufugaijinService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
