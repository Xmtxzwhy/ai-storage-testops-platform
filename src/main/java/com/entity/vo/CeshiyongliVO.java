package com.entity.vo;

import com.entity.CeshiyongliEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 测试用例
 * @author 
 * @email 
 * @date 2025-03-03 22:28:05
 */
public class CeshiyongliVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 用例文件
	 */
	
	private String yongliwenjian;
		
	/**
	 * 功能要求
	 */
	
	private String gongnengyaoqiu;
		
	/**
	 * 存在问题
	 */
	
	private String cunzaiwenti;
		
	/**
	 * 解决需求
	 */
	
	private String jiejuexuqiu;
		
	/**
	 * 详情信息
	 */
	
	private String xiangqingxinxi;
		
	/**
	 * 经理账号
	 */
	
	private String jinglizhanghao;
		
	/**
	 * 登记时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date dengjishijian;
				
	
	/**
	 * 设置：用例文件
	 */
	 
	public void setYongliwenjian(String yongliwenjian) {
		this.yongliwenjian = yongliwenjian;
	}
	
	/**
	 * 获取：用例文件
	 */
	public String getYongliwenjian() {
		return yongliwenjian;
	}
				
	
	/**
	 * 设置：功能要求
	 */
	 
	public void setGongnengyaoqiu(String gongnengyaoqiu) {
		this.gongnengyaoqiu = gongnengyaoqiu;
	}
	
	/**
	 * 获取：功能要求
	 */
	public String getGongnengyaoqiu() {
		return gongnengyaoqiu;
	}
				
	
	/**
	 * 设置：存在问题
	 */
	 
	public void setCunzaiwenti(String cunzaiwenti) {
		this.cunzaiwenti = cunzaiwenti;
	}
	
	/**
	 * 获取：存在问题
	 */
	public String getCunzaiwenti() {
		return cunzaiwenti;
	}
				
	
	/**
	 * 设置：解决需求
	 */
	 
	public void setJiejuexuqiu(String jiejuexuqiu) {
		this.jiejuexuqiu = jiejuexuqiu;
	}
	
	/**
	 * 获取：解决需求
	 */
	public String getJiejuexuqiu() {
		return jiejuexuqiu;
	}
				
	
	/**
	 * 设置：详情信息
	 */
	 
	public void setXiangqingxinxi(String xiangqingxinxi) {
		this.xiangqingxinxi = xiangqingxinxi;
	}
	
	/**
	 * 获取：详情信息
	 */
	public String getXiangqingxinxi() {
		return xiangqingxinxi;
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
	 * 设置：登记时间
	 */
	 
	public void setDengjishijian(Date dengjishijian) {
		this.dengjishijian = dengjishijian;
	}
	
	/**
	 * 获取：登记时间
	 */
	public Date getDengjishijian() {
		return dengjishijian;
	}
			
}
