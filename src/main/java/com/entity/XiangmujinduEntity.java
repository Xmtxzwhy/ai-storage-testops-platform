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
 * 项目进度
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
@TableName("xiangmujindu")
public class XiangmujinduEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public XiangmujinduEntity() {
		
	}
	
	public XiangmujinduEntity(T t) {
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
