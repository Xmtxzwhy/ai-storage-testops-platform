package com.entity.vo;

import com.entity.ZiyuanfenpeiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 资源分配
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public class ZiyuanfenpeiVO  implements Serializable {
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
	 * 资源名称
	 */
	
	private String ziyuanmingcheng;
		
	/**
	 * 分配数量
	 */
	
	private Integer fenpeishuliang;
		
	/**
	 * 备注
	 */
	
	private String beizhu;
		
	/**
	 * 资金
	 */
	
	private String zijin;
		
	/**
	 * 分配人员
	 */
	
	private String fenpeirenyuan;
		
	/**
	 * 分配时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date fenpeishijian;
		
	/**
	 * 经理工号
	 */
	
	private String jingligonghao;
		
	/**
	 * 经理名称
	 */
	
	private String jinglimingcheng;
				
	
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
	 * 设置：资源名称
	 */
	 
	public void setZiyuanmingcheng(String ziyuanmingcheng) {
		this.ziyuanmingcheng = ziyuanmingcheng;
	}
	
	/**
	 * 获取：资源名称
	 */
	public String getZiyuanmingcheng() {
		return ziyuanmingcheng;
	}
				
	
	/**
	 * 设置：分配数量
	 */
	 
	public void setFenpeishuliang(Integer fenpeishuliang) {
		this.fenpeishuliang = fenpeishuliang;
	}
	
	/**
	 * 获取：分配数量
	 */
	public Integer getFenpeishuliang() {
		return fenpeishuliang;
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
	 * 设置：资金
	 */
	 
	public void setZijin(String zijin) {
		this.zijin = zijin;
	}
	
	/**
	 * 获取：资金
	 */
	public String getZijin() {
		return zijin;
	}
				
	
	/**
	 * 设置：分配人员
	 */
	 
	public void setFenpeirenyuan(String fenpeirenyuan) {
		this.fenpeirenyuan = fenpeirenyuan;
	}
	
	/**
	 * 获取：分配人员
	 */
	public String getFenpeirenyuan() {
		return fenpeirenyuan;
	}
				
	
	/**
	 * 设置：分配时间
	 */
	 
	public void setFenpeishijian(Date fenpeishijian) {
		this.fenpeishijian = fenpeishijian;
	}
	
	/**
	 * 获取：分配时间
	 */
	public Date getFenpeishijian() {
		return fenpeishijian;
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
			
}
