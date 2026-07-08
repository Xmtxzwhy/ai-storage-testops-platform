package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 项目信息
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
@TableName("xiangmuxinxi")
public class XiangmuxinxiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public XiangmuxinxiEntity() {
		
	}
	
	public XiangmuxinxiEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 项目编号
	 */
					
	private String xiangmubianhao;
	
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
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
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
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：项目编号
	 */
	public void setXiangmubianhao(String xiangmubianhao) {
		this.xiangmubianhao = xiangmubianhao;
	}
	/**
	 * 获取：项目编号
	 */
	public String getXiangmubianhao() {
		return xiangmubianhao;
	}
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
