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
 * 书籍信息章节表
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-03-27 20:24:07
 */
@TableName("chaptershujixinxi")
public class ChaptershujixinxiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ChaptershujixinxiEntity() {
		
	}
	
	public ChaptershujixinxiEntity(T t) {
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
	 * 关联表id
	 */
					
	private Long refid;
	
	/**
	 * 章节数
	 */
					
	private Integer chapternum;
	
	/**
	 * 章节标题
	 */
					
	private String chaptertitle;
	
	/**
	 * 章节内容
	 */
					
	private String content;
	
	
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
	 * 设置：关联表id
	 */
	public void setRefid(Long refid) {
		this.refid = refid;
	}
	/**
	 * 获取：关联表id
	 */
	public Long getRefid() {
		return refid;
	}
	/**
	 * 设置：章节数
	 */
	public void setChapternum(Integer chapternum) {
		this.chapternum = chapternum;
	}
	/**
	 * 获取：章节数
	 */
	public Integer getChapternum() {
		return chapternum;
	}
	/**
	 * 设置：章节标题
	 */
	public void setChaptertitle(String chaptertitle) {
		this.chaptertitle = chaptertitle;
	}
	/**
	 * 获取：章节标题
	 */
	public String getChaptertitle() {
		return chaptertitle;
	}
	/**
	 * 设置：章节内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：章节内容
	 */
	public String getContent() {
		return content;
	}

}
