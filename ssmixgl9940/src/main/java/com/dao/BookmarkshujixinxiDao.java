package com.dao;

import com.entity.BookmarkshujixinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.BookmarkshujixinxiVO;
import com.entity.view.BookmarkshujixinxiView;


/**
 * 书籍信息书签表
 * 
 * @author 
 * @email 
 * @date 2025-03-27 20:24:07
 */
public interface BookmarkshujixinxiDao extends BaseMapper<BookmarkshujixinxiEntity> {
	
	List<BookmarkshujixinxiVO> selectListVO(@Param("ew") Wrapper<BookmarkshujixinxiEntity> wrapper);
	
	BookmarkshujixinxiVO selectVO(@Param("ew") Wrapper<BookmarkshujixinxiEntity> wrapper);
	
	List<BookmarkshujixinxiView> selectListView(@Param("ew") Wrapper<BookmarkshujixinxiEntity> wrapper);

	List<BookmarkshujixinxiView> selectListView(Pagination page,@Param("ew") Wrapper<BookmarkshujixinxiEntity> wrapper);

	
	BookmarkshujixinxiView selectView(@Param("ew") Wrapper<BookmarkshujixinxiEntity> wrapper);
	

}
