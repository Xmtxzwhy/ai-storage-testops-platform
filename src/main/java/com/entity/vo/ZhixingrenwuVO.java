package com.entity.vo;

import com.entity.ZhixingrenwuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 执行任务
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public class ZhixingrenwuVO  implements Serializable {
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
	 * 测试用例
	 */
	
	private String ceshiyongli;
		
	/**
	 * 执行顺序
	 */
	
	private String zhixingshunxu;
		
	/**
	 * 执行条件
	 */
	
	private String zhixingtiaojian;
		
	/**
	 * 执行时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date zhixingshijian;
		
	/**
	 * 经理账号
	 */
	
	private String jinglizhanghao;
		
	/**
	 * 经理姓名
	 */
	
	private String jinglixingming;
		
	/**
	 * 团队名称
	 */
	
	private String tuanduimingcheng;
		
	/**
	 * 工程师号
	 */
	
	private String gongchengshihao;
		
	/**
	 * 工程师名
	 */
	
	private String gongchengshiming;
		
	/**
	 * 测试结果
	 */
	
	private String ceshijieguo;
		
	/**
	 * 报告缺陷
	 */
	
	private String baogaoquexian;
		
	/**
	 * 状态
	 */
	
	private String zhuangtai;
				
	
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
	 * 设置：测试用例
	 */
	 
	public void setCeshiyongli(String ceshiyongli) {
		this.ceshiyongli = ceshiyongli;
	}
	
	/**
	 * 获取：测试用例
	 */
	public String getCeshiyongli() {
		return ceshiyongli;
	}
				
	
	/**
	 * 设置：执行顺序
	 */
	 
	public void setZhixingshunxu(String zhixingshunxu) {
		this.zhixingshunxu = zhixingshunxu;
	}
	
	/**
	 * 获取：执行顺序
	 */
	public String getZhixingshunxu() {
		return zhixingshunxu;
	}
				
	
	/**
	 * 设置：执行条件
	 */
	 
	public void setZhixingtiaojian(String zhixingtiaojian) {
		this.zhixingtiaojian = zhixingtiaojian;
	}
	
	/**
	 * 获取：执行条件
	 */
	public String getZhixingtiaojian() {
		return zhixingtiaojian;
	}
				
	
	/**
	 * 设置：执行时间
	 */
	 
	public void setZhixingshijian(Date zhixingshijian) {
		this.zhixingshijian = zhixingshijian;
	}
	
	/**
	 * 获取：执行时间
	 */
	public Date getZhixingshijian() {
		return zhixingshijian;
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
	 * 设置：测试结果
	 */
	 
	public void setCeshijieguo(String ceshijieguo) {
		this.ceshijieguo = ceshijieguo;
	}
	
	/**
	 * 获取：测试结果
	 */
	public String getCeshijieguo() {
		return ceshijieguo;
	}
				
	
	/**
	 * 设置：报告缺陷
	 */
	 
	public void setBaogaoquexian(String baogaoquexian) {
		this.baogaoquexian = baogaoquexian;
	}
	
	/**
	 * 获取：报告缺陷
	 */
	public String getBaogaoquexian() {
		return baogaoquexian;
	}
				
	
	/**
	 * 设置：状态
	 */
	 
	public void setZhuangtai(String zhuangtai) {
		this.zhuangtai = zhuangtai;
	}
	
	/**
	 * 获取：状态
	 */
	public String getZhuangtai() {
		return zhuangtai;
	}
			
}
