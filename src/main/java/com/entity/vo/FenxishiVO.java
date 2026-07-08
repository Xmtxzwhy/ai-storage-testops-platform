package com.entity.vo;

import com.entity.FenxishiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * QA分析师
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public class FenxishiVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 分析师名
	 */
	
	private String fenxishiming;
		
	/**
	 * 密码
	 */
	
	private String mima;
		
	/**
	 * 性别
	 */
	
	private String xingbie;
		
	/**
	 * 电话号码
	 */
	
	private String dianhuahaoma;
				
	
	/**
	 * 设置：分析师名
	 */
	 
	public void setFenxishiming(String fenxishiming) {
		this.fenxishiming = fenxishiming;
	}
	
	/**
	 * 获取：分析师名
	 */
	public String getFenxishiming() {
		return fenxishiming;
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
	 * 设置：电话号码
	 */
	 
	public void setDianhuahaoma(String dianhuahaoma) {
		this.dianhuahaoma = dianhuahaoma;
	}
	
	/**
	 * 获取：电话号码
	 */
	public String getDianhuahaoma() {
		return dianhuahaoma;
	}
			
}
