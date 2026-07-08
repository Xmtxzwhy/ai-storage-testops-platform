package com.entity.view;

import com.entity.CeshiyongliEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 测试用例
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-03-03 22:28:05
 */
@TableName("ceshiyongli")
public class CeshiyongliView  extends CeshiyongliEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public CeshiyongliView(){
	}
 
 	public CeshiyongliView(CeshiyongliEntity ceshiyongliEntity){
 	try {
			BeanUtils.copyProperties(this, ceshiyongliEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
