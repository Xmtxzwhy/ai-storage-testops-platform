package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.entity.TokenEntity;
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

import com.entity.FenxishiEntity;
import com.entity.view.FenxishiView;

import com.service.FenxishiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * QA分析师
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
@RestController
@RequestMapping("/fenxishi")
public class FenxishiController {
    @Autowired
    private FenxishiService fenxishiService;






    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据登录查询用户信息
        FenxishiEntity u = fenxishiService.selectOne(new EntityWrapper<FenxishiEntity>().eq("fenxishihao", username));
        // 当用户不存在或验证密码不通过时
		if(u==null || !u.getMima().equals(password)) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
		
        // 获取登录token
		String token = tokenService.generateToken(u.getId(), username,"fenxishi",  "分析师" );
        //返回token
		return R.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody FenxishiEntity fenxishi){
    	//ValidatorUtils.validateEntity(fenxishi);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	FenxishiEntity u = fenxishiService.selectOne(new EntityWrapper<FenxishiEntity>().eq("fenxishihao", fenxishi.getFenxishihao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        //判断是否存在相同分析师号，否则返回错误信息
        if(fenxishiService.selectCount(new EntityWrapper<FenxishiEntity>().eq("fenxishihao", fenxishi.getFenxishihao()))>0) {
            return R.error("分析师号已存在");
        }
		Long uId = new Date().getTime();
		fenxishi.setId(uId);
        //保存用户
        fenxishiService.insert(fenxishi);
        return R.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        FenxishiEntity u = fenxishiService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	//根据登录账号判断是否存在用户信息，否则返回错误信息
        FenxishiEntity u = fenxishiService.selectOne(new EntityWrapper<FenxishiEntity>().eq("fenxishihao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456
        u.setMima("123456");
        fenxishiService.updateById(u);
        return R.ok("密码已重置为：123456");
    }



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,FenxishiEntity fenxishi,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<FenxishiEntity> ew = new EntityWrapper<FenxishiEntity>();


        //查询结果
		PageUtils page = fenxishiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fenxishi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,FenxishiEntity fenxishi, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<FenxishiEntity> ew = new EntityWrapper<FenxishiEntity>();

        //查询结果
		PageUtils page = fenxishiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, fenxishi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( FenxishiEntity fenxishi){
       	EntityWrapper<FenxishiEntity> ew = new EntityWrapper<FenxishiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( fenxishi, "fenxishi")); 
        return R.ok().put("data", fenxishiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(FenxishiEntity fenxishi){
        EntityWrapper< FenxishiEntity> ew = new EntityWrapper< FenxishiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( fenxishi, "fenxishi")); 
		FenxishiView fenxishiView =  fenxishiService.selectView(ew);
		return R.ok("查询QA分析师成功").put("data", fenxishiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        FenxishiEntity fenxishi = fenxishiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(fenxishi,deSens);
        return R.ok().put("data", fenxishi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        FenxishiEntity fenxishi = fenxishiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(fenxishi,deSens);
        return R.ok().put("data", fenxishi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody FenxishiEntity fenxishi, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(fenxishiService.selectCount(new EntityWrapper<FenxishiEntity>().eq("fenxishihao", fenxishi.getFenxishihao()))>0) {
            return R.error("分析师号已存在");
        }
        //ValidatorUtils.validateEntity(fenxishi);
        //验证账号唯一性，否则返回错误信息
        FenxishiEntity u = fenxishiService.selectOne(new EntityWrapper<FenxishiEntity>().eq("fenxishihao", fenxishi.getFenxishihao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	fenxishi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		fenxishi.setId(new Date().getTime());
        fenxishiService.insert(fenxishi);
        return R.ok().put("data",fenxishi.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody FenxishiEntity fenxishi, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(fenxishiService.selectCount(new EntityWrapper<FenxishiEntity>().eq("fenxishihao", fenxishi.getFenxishihao()))>0) {
            return R.error("分析师号已存在");
        }
        //ValidatorUtils.validateEntity(fenxishi);
        //验证账号唯一性，否则返回错误信息
        FenxishiEntity u = fenxishiService.selectOne(new EntityWrapper<FenxishiEntity>().eq("fenxishihao", fenxishi.getFenxishihao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	fenxishi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		fenxishi.setId(new Date().getTime());
        fenxishiService.insert(fenxishi);
        return R.ok().put("data",fenxishi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody FenxishiEntity fenxishi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(fenxishi);
        //验证字段唯一性，否则返回错误信息
        if(fenxishiService.selectCount(new EntityWrapper<FenxishiEntity>().ne("id", fenxishi.getId()).eq("fenxishihao", fenxishi.getFenxishihao()))>0) {
            return R.error("分析师号已存在");
        }
        //全部更新
        fenxishiService.updateById(fenxishi);
        if(null!=fenxishi.getFenxishihao())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(fenxishi.getFenxishihao());
            tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", fenxishi.getId()));
        }
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        fenxishiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
