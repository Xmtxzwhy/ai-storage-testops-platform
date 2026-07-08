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

import com.entity.CeshirenwuEntity;
import com.entity.view.CeshirenwuView;

import com.service.CeshirenwuService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 测试任务
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
@RestController
@RequestMapping("/ceshirenwu")
public class CeshirenwuController {
    @Autowired
    private CeshirenwuService ceshirenwuService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,CeshirenwuEntity ceshirenwu,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("ceshijingli")) {
			ceshirenwu.setJinglizhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("ceshigongchengshi")) {
			ceshirenwu.setGongchengshihao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<CeshirenwuEntity> ew = new EntityWrapper<CeshirenwuEntity>();


        //查询结果
		PageUtils page = ceshirenwuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ceshirenwu), params), params));
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
    public R list(@RequestParam Map<String, Object> params,CeshirenwuEntity ceshirenwu, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<CeshirenwuEntity> ew = new EntityWrapper<CeshirenwuEntity>();

        //查询结果
		PageUtils page = ceshirenwuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ceshirenwu), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( CeshirenwuEntity ceshirenwu){
       	EntityWrapper<CeshirenwuEntity> ew = new EntityWrapper<CeshirenwuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( ceshirenwu, "ceshirenwu")); 
        return R.ok().put("data", ceshirenwuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(CeshirenwuEntity ceshirenwu){
        EntityWrapper< CeshirenwuEntity> ew = new EntityWrapper< CeshirenwuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( ceshirenwu, "ceshirenwu")); 
		CeshirenwuView ceshirenwuView =  ceshirenwuService.selectView(ew);
		return R.ok("查询测试任务成功").put("data", ceshirenwuView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CeshirenwuEntity ceshirenwu = ceshirenwuService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(ceshirenwu,deSens);
        return R.ok().put("data", ceshirenwu);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CeshirenwuEntity ceshirenwu = ceshirenwuService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(ceshirenwu,deSens);
        return R.ok().put("data", ceshirenwu);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CeshirenwuEntity ceshirenwu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ceshirenwu);
        ceshirenwuService.insert(ceshirenwu);
        return R.ok().put("data",ceshirenwu.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody CeshirenwuEntity ceshirenwu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ceshirenwu);
        ceshirenwuService.insert(ceshirenwu);
        return R.ok().put("data",ceshirenwu.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody CeshirenwuEntity ceshirenwu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ceshirenwu);
        //全部更新
        ceshirenwuService.updateById(ceshirenwu);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        ceshirenwuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
