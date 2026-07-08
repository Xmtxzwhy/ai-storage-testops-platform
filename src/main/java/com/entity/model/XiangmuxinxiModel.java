package com.entity.model;

import com.entity.XiangmuxinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 项目信息
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public class XiangmuxinxiModel  implements Serializable {
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
	 * 项目详情
	 */
	
	private String xiangmuxiangqing;
		
	/**
	 * 文档
	 */
	
	private String wendang;
		
	/**
	 * 备注
	 */
	
	private String beizhu;
		
	/**
	 * 登记时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date dengjishijian;
		
	/**
	 * 经理工号
	 */
	
	private String jingligonghao;
		
	/**
	 * 经理名称
	 */
	
	private String jinglimingcheng;
		
	/**
	 * 项目规划
	 */
	
	private String xiangmuguihua;
				
	
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
	 * 设置：项目详情
	 */
	 
	public void setXiangmuxiangqing(String xiangmuxiangqing) {
		this.xiangmuxiangqing = xiangmuxiangqing;
	}
	
	/**
	 * 获取：项目详情
	 */
	public String getXiangmuxiangqing() {
		return xiangmuxiangqing;
	}
				
	
	/**
	 * 设置：文档
	 */
	 
	public void setWendang(String wendang) {
		this.wendang = wendang;
	}
	
	/**
	 * 获取：文档
	 */
	public String getWendang() {
		return wendang;
	}
				
	
	/**
	 * 设置：备注
	 */
	 
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	
	/**
	 * 获取：备注
	 */
	public String getBeizhu() {
		return beizhu;
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
				
	
	/**
	 * 设置：经理工号
	 */
	 
	public void setJingligonghao(String jingligonghao) {
		this.jingligonghao = jingligonghao;
	}
	
	/**
	 * 获取：经理工号
	 */
	public String getJingligonghao() {
		return jingligonghao;
	}
				
	
	/**
	 * 设置：经理名称
	 */
	 
	public void setJinglimingcheng(String jinglimingcheng) {
		this.jinglimingcheng = jinglimingcheng;
	}
	
	/**
	 * 获取：经理名称
	 */
	public String getJinglimingcheng() {
		return jinglimingcheng;
	}
				
	
	/**
	 * 设置：项目规划
	 */
	 
	public void setXiangmuguihua(String xiangmuguihua) {
		this.xiangmuguihua = xiangmuguihua;
	}
	
	/**
	 * 获取：项目规划
	 */
	public String getXiangmuguihua() {
		return xiangmuguihua;
	}
			
}
