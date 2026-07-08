package com.entity.model;

import com.entity.CeshijihuaEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 测试计划
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public class CeshijihuaModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 项目名称
	 */
	
	private String xiangmumingcheng;
		
	/**
	 * 项目类型
	 */
	
	private String xiangmuleixing;
		
	/**
	 * 计划附件
	 */
	
	private String jihuafujian;
		
	/**
	 * 测试策略
	 */
	
	private String ceshicelve;
		
	/**
	 * 计划详情
	 */
	
	private String jihuaxiangqing;
		
	/**
	 * 计划时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date jihuashijian;
		
	/**
	 * 经理账号
	 */
	
	private String jinglizhanghao;
		
	/**
	 * 经理姓名
	 */
	
	private String jinglixingming;
		
	/**
	 * 团队名称
	 */
	
	private String tuanduimingcheng;
				
	
	/**
	 * 设置：项目名称
	 */
	 
	public void setXiangmumingcheng(String xiangmumingcheng) {
		this.xiangmumingcheng = xiangmumingcheng;
	}
	
	/**
	 * 获取：项目名称
	 */
	public String getXiangmumingcheng() {
		return xiangmumingcheng;
	}
				
	
	/**
	 * 设置：项目类型
	 */
	 
	public void setXiangmuleixing(String xiangmuleixing) {
		this.xiangmuleixing = xiangmuleixing;
	}
	
	/**
	 * 获取：项目类型
	 */
	public String getXiangmuleixing() {
		return xiangmuleixing;
	}
				
	
	/**
	 * 设置：计划附件
	 */
	 
	public void setJihuafujian(String jihuafujian) {
		this.jihuafujian = jihuafujian;
	}
	
	/**
	 * 获取：计划附件
	 */
	public String getJihuafujian() {
		return jihuafujian;
	}
				
	
	/**
	 * 设置：测试策略
	 */
	 
	public void setCeshicelve(String ceshicelve) {
		this.ceshicelve = ceshicelve;
	}
	
	/**
	 * 获取：测试策略
	 */
	public String getCeshicelve() {
		return ceshicelve;
	}
				
	
	/**
	 * 设置：计划详情
	 */
	 
	public void setJihuaxiangqing(String jihuaxiangqing) {
		this.jihuaxiangqing = jihuaxiangqing;
	}
	
	/**
	 * 获取：计划详情
	 */
	public String getJihuaxiangqing() {
		return jihuaxiangqing;
	}
				
	
	/**
	 * 设置：计划时间
	 */
	 
	public void setJihuashijian(Date jihuashijian) {
		this.jihuashijian = jihuashijian;
	}
	
	/**
	 * 获取：计划时间
	 */
	public Date getJihuashijian() {
		return jihuashijian;
	}
				
	
	/**
	 * 设置：经理账号
	 */
	 
	public void setJinglizhanghao(String jinglizhanghao) {
		this.jinglizhanghao = jinglizhanghao;
	}
	
	/**
	 * 获取：经理账号
	 */
	public String getJinglizhanghao() {
		return jinglizhanghao;
	}
				
	
	/**
	 * 设置：经理姓名
	 */
	 
	public void setJinglixingming(String jinglixingming) {
		this.jinglixingming = jinglixingming;
	}
	
	/**
	 * 获取：经理姓名
	 */
	public String getJinglixingming() {
		return jinglixingming;
	}
				
	
	/**
	 * 设置：团队名称
	 */
	 
	public void setTuanduimingcheng(String tuanduimingcheng) {
		this.tuanduimingcheng = tuanduimingcheng;
	}
	
	/**
	 * 获取：团队名称
	 */
	public String getTuanduimingcheng() {
		return tuanduimingcheng;
	}
			
}
