package com.entity.view;

import com.entity.CeshifenxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 测试分析
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-03-03 22:28:05
 */
@TableName("ceshifenxi")
public class CeshifenxiView  extends CeshifenxiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public CeshifenxiView(){
	}
 
 	public CeshifenxiView(CeshifenxiEntity ceshifenxiEntity){
 	try {
			BeanUtils.copyProperties(this, ceshifenxiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
