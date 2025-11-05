package com.entity.view;

import com.entity.DiscussshujixinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 书籍信息评论表
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-03-27 20:24:06
 */
@TableName("discussshujixinxi")
public class DiscussshujixinxiView  extends DiscussshujixinxiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public DiscussshujixinxiView(){
	}
 
 	public DiscussshujixinxiView(DiscussshujixinxiEntity discussshujixinxiEntity){
 	try {
			BeanUtils.copyProperties(this, discussshujixinxiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
