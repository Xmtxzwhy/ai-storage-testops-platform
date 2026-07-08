package com.entity.vo;

import com.entity.CeshituanduiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 测试团队
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public class CeshituanduiVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 联系方式
	 */
	
	private String lianxifangshi;
		
	/**
	 * 成立时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date chenglishijian;
		
	/**
	 * 团队成员
	 */
	
	private String tuanduichengyuan;
		
	/**
	 * 团队介绍
	 */
	
	private String tuanduijieshao;
				
	
	/**
	 * 设置：联系方式
	 */
	 
	public void setLianxifangshi(String lianxifangshi) {
		this.lianxifangshi = lianxifangshi;
	}
	
	/**
	 * 获取：联系方式
	 */
	public String getLianxifangshi() {
		return lianxifangshi;
	}
				
	
	/**
	 * 设置：成立时间
	 */
	 
	public void setChenglishijian(Date chenglishijian) {
		this.chenglishijian = chenglishijian;
	}
	
	/**
	 * 获取：成立时间
	 */
	public Date getChenglishijian() {
		return chenglishijian;
	}
				
	
	/**
	 * 设置：团队成员
	 */
	 
	public void setTuanduichengyuan(String tuanduichengyuan) {
		this.tuanduichengyuan = tuanduichengyuan;
	}
	
	/**
	 * 获取：团队成员
	 */
	public String getTuanduichengyuan() {
		return tuanduichengyuan;
	}
				
	
	/**
	 * 设置：团队介绍
	 */
	 
	public void setTuanduijieshao(String tuanduijieshao) {
		this.tuanduijieshao = tuanduijieshao;
	}
	
	/**
	 * 获取：团队介绍
	 */
	public String getTuanduijieshao() {
		return tuanduijieshao;
	}
			
}
