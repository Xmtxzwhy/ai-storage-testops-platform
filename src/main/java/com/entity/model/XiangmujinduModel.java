package com.entity.model;

import com.entity.XiangmujinduEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 项目进度
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public class XiangmujinduModel  implements Serializable {
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
	 * 项目进度
	 */
	
	private String xiangmujindu;
		
	/**
	 * 进度情况
	 */
	
	private String jinduqingkuang;
		
	/**
	 * 进度报告
	 */
	
	private String jindubaogao;
		
	/**
	 * 上传时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date shangchuanshijian;
		
	/**
	 * 经理名称
	 */
	
	private String jinglimingcheng;
		
	/**
	 * 经理工号
	 */
	
	private String jingligonghao;
				
	
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
	 * 设置：项目进度
	 */
	 
	public void setXiangmujindu(String xiangmujindu) {
		this.xiangmujindu = xiangmujindu;
	}
	
	/**
	 * 获取：项目进度
	 */
	public String getXiangmujindu() {
		return xiangmujindu;
	}
				
	
	/**
	 * 设置：进度情况
	 */
	 
	public void setJinduqingkuang(String jinduqingkuang) {
		this.jinduqingkuang = jinduqingkuang;
	}
	
	/**
	 * 获取：进度情况
	 */
	public String getJinduqingkuang() {
		return jinduqingkuang;
	}
				
	
	/**
	 * 设置：进度报告
	 */
	 
	public void setJindubaogao(String jindubaogao) {
		this.jindubaogao = jindubaogao;
	}
	
	/**
	 * 获取：进度报告
	 */
	public String getJindubaogao() {
		return jindubaogao;
	}
				
	
	/**
	 * 设置：上传时间
	 */
	 
	public void setShangchuanshijian(Date shangchuanshijian) {
		this.shangchuanshijian = shangchuanshijian;
	}
	
	/**
	 * 获取：上传时间
	 */
	public Date getShangchuanshijian() {
		return shangchuanshijian;
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
			
}
