package com.entity.vo;

import com.entity.XiufugaijinEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 修复改进
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
public class XiufugaijinVO  implements Serializable {
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
	 * 缺陷修复
	 */
	
	private String quexianxiufu;
		
	/**
	 * 代码改进
	 */
	
	private String daimagaijin;
		
	/**
	 * 开发账号
	 */
	
	private String kaifazhanghao;
		
	/**
	 * 开发姓名
	 */
	
	private String kaifaxingming;
		
	/**
	 * 改进时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date gaijinshijian;
		
	/**
	 * 测试状态
	 */
	
	private String ceshizhuangtai;
				
	
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
	 * 设置：缺陷修复
	 */
	 
	public void setQuexianxiufu(String quexianxiufu) {
		this.quexianxiufu = quexianxiufu;
	}
	
	/**
	 * 获取：缺陷修复
	 */
	public String getQuexianxiufu() {
		return quexianxiufu;
	}
				
	
	/**
	 * 设置：代码改进
	 */
	 
	public void setDaimagaijin(String daimagaijin) {
		this.daimagaijin = daimagaijin;
	}
	
	/**
	 * 获取：代码改进
	 */
	public String getDaimagaijin() {
		return daimagaijin;
	}
				
	
	/**
	 * 设置：开发账号
	 */
	 
	public void setKaifazhanghao(String kaifazhanghao) {
		this.kaifazhanghao = kaifazhanghao;
	}
	
	/**
	 * 获取：开发账号
	 */
	public String getKaifazhanghao() {
		return kaifazhanghao;
	}
				
	
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
	 * 设置：改进时间
	 */
	 
	public void setGaijinshijian(Date gaijinshijian) {
		this.gaijinshijian = gaijinshijian;
	}
	
	/**
	 * 获取：改进时间
	 */
	public Date getGaijinshijian() {
		return gaijinshijian;
	}
				
	
	/**
	 * 设置：测试状态
	 */
	 
	public void setCeshizhuangtai(String ceshizhuangtai) {
		this.ceshizhuangtai = ceshizhuangtai;
	}
	
	/**
	 * 获取：测试状态
	 */
	public String getCeshizhuangtai() {
		return ceshizhuangtai;
	}
			
}
