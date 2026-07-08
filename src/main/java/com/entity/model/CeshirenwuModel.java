package com.entity.model;

import com.entity.CeshirenwuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 测试任务
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public class CeshirenwuModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 任务名称
	 */
	
	private String renwumingcheng;
		
	/**
	 * 项目名称
	 */
	
	private String xiangmumingcheng;
		
	/**
	 * 项目类型
	 */
	
	private String xiangmuleixing;
		
	/**
	 * 任务文件
	 */
	
	private String renwuwenjian;
		
	/**
	 * 任务内容
	 */
	
	private String renwuneirong;
		
	/**
	 * 下发时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date xiafashijian;
		
	/**
	 * 经理账号
	 */
	
	private String jinglizhanghao;
		
	/**
	 * 经理姓名
	 */
	
	private String jinglixingming;
		
	/**
	 * 工程师号
	 */
	
	private String gongchengshihao;
		
	/**
	 * 工程师名
	 */
	
	private String gongchengshiming;
		
	/**
	 * 团队名称
	 */
	
	private String tuanduimingcheng;
		
	/**
	 * 任务状态
	 */
	
	private String renwuzhuangtai;
				
	
	/**
	 * 设置：任务名称
	 */
	 
	public void setRenwumingcheng(String renwumingcheng) {
		this.renwumingcheng = renwumingcheng;
	}
	
	/**
	 * 获取：任务名称
	 */
	public String getRenwumingcheng() {
		return renwumingcheng;
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
	 * 设置：任务文件
	 */
	 
	public void setRenwuwenjian(String renwuwenjian) {
		this.renwuwenjian = renwuwenjian;
	}
	
	/**
	 * 获取：任务文件
	 */
	public String getRenwuwenjian() {
		return renwuwenjian;
	}
				
	
	/**
	 * 设置：任务内容
	 */
	 
	public void setRenwuneirong(String renwuneirong) {
		this.renwuneirong = renwuneirong;
	}
	
	/**
	 * 获取：任务内容
	 */
	public String getRenwuneirong() {
		return renwuneirong;
	}
				
	
	/**
	 * 设置：下发时间
	 */
	 
	public void setXiafashijian(Date xiafashijian) {
		this.xiafashijian = xiafashijian;
	}
	
	/**
	 * 获取：下发时间
	 */
	public Date getXiafashijian() {
		return xiafashijian;
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
	 * 设置：工程师号
	 */
	 
	public void setGongchengshihao(String gongchengshihao) {
		this.gongchengshihao = gongchengshihao;
	}
	
	/**
	 * 获取：工程师号
	 */
	public String getGongchengshihao() {
		return gongchengshihao;
	}
				
	
	/**
	 * 设置：工程师名
	 */
	 
	public void setGongchengshiming(String gongchengshiming) {
		this.gongchengshiming = gongchengshiming;
	}
	
	/**
	 * 获取：工程师名
	 */
	public String getGongchengshiming() {
		return gongchengshiming;
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
				
	
	/**
	 * 设置：任务状态
	 */
	 
	public void setRenwuzhuangtai(String renwuzhuangtai) {
		this.renwuzhuangtai = renwuzhuangtai;
	}
	
	/**
	 * 获取：任务状态
	 */
	public String getRenwuzhuangtai() {
		return renwuzhuangtai;
	}
			
}
