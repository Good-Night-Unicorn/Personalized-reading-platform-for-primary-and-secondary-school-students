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
 * 书籍信息书签表
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-03-27 20:24:07
 */
@TableName("bookmarkshujixinxi")
public class BookmarkshujixinxiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public BookmarkshujixinxiEntity() {
		
	}
	
	public BookmarkshujixinxiEntity(T t) {
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
	 * 章节表id
	 */
					
	private Long chapterid;
	
	/**
	 * 章节数
	 */
					
	private Integer chapternum;
	
	/**
	 * 章节标题
	 */
					
	private String chaptertitle;
	
	/**
	 * 用户id
	 */
					
	private Long userid;
	
	/**
	 * 进度
	 */
					
	private Integer progress;
	
	
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
	 * 设置：章节表id
	 */
	public void setChapterid(Long chapterid) {
		this.chapterid = chapterid;
	}
	/**
	 * 获取：章节表id
	 */
	public Long getChapterid() {
		return chapterid;
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
	 * 设置：用户id
	 */
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	/**
	 * 获取：用户id
	 */
	public Long getUserid() {
		return userid;
	}
	/**
	 * 设置：进度
	 */
	public void setProgress(Integer progress) {
		this.progress = progress;
	}
	/**
	 * 获取：进度
	 */
	public Integer getProgress() {
		return progress;
	}

}
