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

import com.entity.CeshifenxiEntity;
import com.entity.view.CeshifenxiView;

import com.service.CeshifenxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 测试分析
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-03 22:28:05
 */
@RestController
@RequestMapping("/ceshifenxi")
public class CeshifenxiController {
    @Autowired
    private CeshifenxiService ceshifenxiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,CeshifenxiEntity ceshifenxi,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("ceshijingli")) {
			ceshifenxi.setJinglizhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("ceshigongchengshi")) {
			ceshifenxi.setGongchengshihao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("kaifarenyuan")) {
			ceshifenxi.setKaifazhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("fenxishi")) {
			ceshifenxi.setFenxishihao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<CeshifenxiEntity> ew = new EntityWrapper<CeshifenxiEntity>();


        //查询结果
		PageUtils page = ceshifenxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ceshifenxi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,CeshifenxiEntity ceshifenxi, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<CeshifenxiEntity> ew = new EntityWrapper<CeshifenxiEntity>();

        //查询结果
		PageUtils page = ceshifenxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ceshifenxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( CeshifenxiEntity ceshifenxi){
       	EntityWrapper<CeshifenxiEntity> ew = new EntityWrapper<CeshifenxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( ceshifenxi, "ceshifenxi")); 
        return R.ok().put("data", ceshifenxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(CeshifenxiEntity ceshifenxi){
        EntityWrapper< CeshifenxiEntity> ew = new EntityWrapper< CeshifenxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( ceshifenxi, "ceshifenxi")); 
		CeshifenxiView ceshifenxiView =  ceshifenxiService.selectView(ew);
		return R.ok("查询测试分析成功").put("data", ceshifenxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CeshifenxiEntity ceshifenxi = ceshifenxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(ceshifenxi,deSens);
        return R.ok().put("data", ceshifenxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CeshifenxiEntity ceshifenxi = ceshifenxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(ceshifenxi,deSens);
        return R.ok().put("data", ceshifenxi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CeshifenxiEntity ceshifenxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ceshifenxi);
        ceshifenxiService.insert(ceshifenxi);
        return R.ok().put("data",ceshifenxi.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody CeshifenxiEntity ceshifenxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ceshifenxi);
        ceshifenxiService.insert(ceshifenxi);
        return R.ok().put("data",ceshifenxi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody CeshifenxiEntity ceshifenxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ceshifenxi);
        //全部更新
        ceshifenxiService.updateById(ceshifenxi);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        ceshifenxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    






    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_ceshifenxi_" + xColumnName + "_" + yColumnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        //构建查询统计条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        EntityWrapper<CeshifenxiEntity> ew = new EntityWrapper<CeshifenxiEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
                                        if(tableName.equals("ceshijingli")) {
            ew.eq("jinglizhanghao", (String)request.getSession().getAttribute("username"));
        }
                        if(tableName.equals("ceshigongchengshi")) {
            ew.eq("gongchengshihao", (String)request.getSession().getAttribute("username"));
        }
                            if(tableName.equals("kaifarenyuan")) {
            ew.eq("kaifazhanghao", (String)request.getSession().getAttribute("username"));
        }
                                if(tableName.equals("fenxishi")) {
            ew.eq("fenxishihao", (String)request.getSession().getAttribute("username"));
        }
                    //获取结果
        List<Map<String, Object>> result = ceshifenxiService.selectValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        Collections.sort(result, (map1, map2) -> {
            // 假设 total 总是存在并且是数值类型
            Number total1 = (Number) map1.get("total");
            Number total2 = (Number) map2.get("total");
            if(total1==null)
            {
                total1 = 0;
            }
            if(total2==null)
            {
                total2 = 0;
            }
            return Double.compare(total2.doubleValue(), total1.doubleValue());
        });
        return R.ok().put("data", result);
    }
    
    /**
     * （按值统计(多)）
     */
    @RequestMapping("/valueMul/{xColumnName}")
    public R valueMul(@PathVariable("xColumnName") String xColumnName,@RequestParam String yColumnNameMul,HttpServletRequest request)  throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_ceshifenxi_" + xColumnName + "_" + yColumnNameMul + "_timeType.json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //构建查询统计条件
        EntityWrapper<CeshifenxiEntity> ew = new EntityWrapper<CeshifenxiEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("ceshijingli")) {
            ew.eq("jinglizhanghao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("ceshigongchengshi")) {
            ew.eq("gongchengshihao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("kaifarenyuan")) {
            ew.eq("kaifazhanghao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("fenxishi")) {
            ew.eq("fenxishihao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = ceshifenxiService.selectValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }
    
    /**
     * （按值统计）时间统计类型
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}/{timeStatType}")
    public R valueDay(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_ceshifenxi_" + xColumnName + "_" + yColumnName + "_"+timeStatType+".json");
        if(java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("timeStatType", timeStatType);
        //构建查询统计条件
        EntityWrapper<CeshifenxiEntity> ew = new EntityWrapper<CeshifenxiEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("ceshijingli")) {
            ew.eq("jinglizhanghao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("ceshigongchengshi")) {
            ew.eq("gongchengshihao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("kaifarenyuan")) {
            ew.eq("kaifazhanghao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("fenxishi")) {
            ew.eq("fenxishihao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = ceshifenxiService.selectTimeStatValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }
    
    /**
     * （按值统计）时间统计类型(多)
     */
    @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
    public R valueMulDay(@PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,@RequestParam String yColumnNameMul,HttpServletRequest request) throws IOException
    {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("value_ceshifenxi_" + xColumnName + "_" + yColumnNameMul + "_" + timeStatType + ".json");
        if (java.nio.file.Files.exists(path)) {
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("timeStatType", timeStatType);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //构建查询统计条件
        EntityWrapper<CeshifenxiEntity> ew = new EntityWrapper<CeshifenxiEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("ceshijingli")) {
            ew.eq("jinglizhanghao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("ceshigongchengshi")) {
            ew.eq("gongchengshihao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("kaifarenyuan")) {
            ew.eq("kaifazhanghao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("fenxishi")) {
            ew.eq("fenxishihao", (String)request.getSession().getAttribute("username"));
        }
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = ceshifenxiService.selectTimeStatValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }
    
    /**
     * 分组统计
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName,HttpServletRequest request) throws IOException {
        //读取文件，如果文件存在，则优先返回文件内容
        java.nio.file.Path path = java.nio.file.Paths.get("group_ceshifenxi_" + columnName + "_timeType.json");
        if(java.nio.file.Files.exists(path)){
            String content = new String(java.nio.file.Files.readAllBytes(path), java.nio.charset.StandardCharsets.UTF_8);
            return R.ok().put("data", (new org.json.JSONArray(content)).toList());
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", columnName);
        //构建查询统计条件
        EntityWrapper<CeshifenxiEntity> ew = new EntityWrapper<CeshifenxiEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("ceshijingli")) {
            ew.eq("jinglizhanghao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("ceshigongchengshi")) {
            ew.eq("gongchengshihao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("kaifarenyuan")) {
            ew.eq("kaifazhanghao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("fenxishi")) {
            ew.eq("fenxishihao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = ceshifenxiService.selectGroup(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }    


    /**
     * 分组统计
     */
    @RequestMapping("/typeStat/ceshijieguo/renwumingcheng")
    public R renwumingchengceshijieguoTypeStat(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        EntityWrapper<CeshifenxiEntity> ew = new EntityWrapper<CeshifenxiEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("ceshijingli")) {
            ew.eq("jinglizhanghao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("ceshigongchengshi")) {
            ew.eq("gongchengshihao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("kaifarenyuan")) {
            ew.eq("kaifazhanghao", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("fenxishi")) {
            ew.eq("fenxishihao", (String)request.getSession().getAttribute("username"));
        }
        List<Map<String, Object>> result = ceshifenxiService.renwumingchengceshijieguoTypeStat(params, ew);
        return R.ok().put("data", result);
    }


    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,CeshifenxiEntity ceshifenxi, HttpServletRequest request){
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("ceshijingli")) {
            ceshifenxi.setJinglizhanghao((String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("ceshigongchengshi")) {
            ceshifenxi.setGongchengshihao((String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("kaifarenyuan")) {
            ceshifenxi.setKaifazhanghao((String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("fenxishi")) {
            ceshifenxi.setFenxishihao((String)request.getSession().getAttribute("username"));
        }
        EntityWrapper<CeshifenxiEntity> ew = new EntityWrapper<CeshifenxiEntity>();
        int count = ceshifenxiService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ceshifenxi), params), params));
        return R.ok().put("data", count);
    }

}
