package com.dao;

import com.entity.DiscussshujixinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussshujixinxiVO;
import com.entity.view.DiscussshujixinxiView;


/**
 * 书籍信息评论表
 * 
 * @author 
 * @email 
 * @date 2025-03-27 20:24:06
 */
public interface DiscussshujixinxiDao extends BaseMapper<DiscussshujixinxiEntity> {
	
	List<DiscussshujixinxiVO> selectListVO(@Param("ew") Wrapper<DiscussshujixinxiEntity> wrapper);
	
	DiscussshujixinxiVO selectVO(@Param("ew") Wrapper<DiscussshujixinxiEntity> wrapper);
	
	List<DiscussshujixinxiView> selectListView(@Param("ew") Wrapper<DiscussshujixinxiEntity> wrapper);

	List<DiscussshujixinxiView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussshujixinxiEntity> wrapper);

	
	DiscussshujixinxiView selectView(@Param("ew") Wrapper<DiscussshujixinxiEntity> wrapper);
	

}
