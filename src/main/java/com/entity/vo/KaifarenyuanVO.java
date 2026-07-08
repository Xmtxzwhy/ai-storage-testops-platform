package com.entity.vo;

import com.entity.KaifarenyuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 开发人员
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public class KaifarenyuanVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 开发姓名
	 */
	
	private String kaifaxingming;
		
	/**
	 * 密码
	 */
	
	private String mima;
		
	/**
	 * 性别
	 */
	
	private String xingbie;
		
	/**
	 * 开发电话
	 */
	
	private String kaifadianhua;
		
	/**
	 * 团队名称
	 */
	
	private String tuanduimingcheng;
				
	
	/**
	 * 设置：开发姓名
	 */
	 
	public void setKaifaxingming(String kaifaxingming) {
		this.kaifaxingming = kaifaxingming;
	}
	
	/**
	 * 获取：开发姓名
	 */
	public String getKaifaxingming() {
		return kaifaxingming;
	}
				
	
	/**
	 * 设置：密码
	 */
	 
	public void setMima(String mima) {
		this.mima = mima;
	}
	
	/**
	 * 获取：密码
	 */
	public String getMima() {
		return mima;
	}
				
	
	/**
	 * 设置：性别
	 */
	 
	public void setXingbie(String xingbie) {
		this.xingbie = xingbie;
	}
	
	/**
	 * 获取：性别
	 */
	public String getXingbie() {
		return xingbie;
	}
				
	
	/**
	 * 设置：开发电话
	 */
	 
	public void setKaifadianhua(String kaifadianhua) {
		this.kaifadianhua = kaifadianhua;
	}
	
	/**
	 * 获取：开发电话
	 */
	public String getKaifadianhua() {
		return kaifadianhua;
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
