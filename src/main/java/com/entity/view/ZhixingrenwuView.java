package com.entity.view;

import com.entity.ZhixingrenwuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 执行任务
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-03-03 22:28:04
 */
@TableName("zhixingrenwu")
public class ZhixingrenwuView  extends ZhixingrenwuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public ZhixingrenwuView(){
	}
 
 	public ZhixingrenwuView(ZhixingrenwuEntity zhixingrenwuEntity){
 	try {
			BeanUtils.copyProperties(this, zhixingrenwuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
