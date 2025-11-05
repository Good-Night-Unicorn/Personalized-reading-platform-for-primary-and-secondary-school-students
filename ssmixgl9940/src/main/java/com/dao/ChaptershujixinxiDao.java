package com.dao;

import com.entity.ChaptershujixinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ChaptershujixinxiVO;
import com.entity.view.ChaptershujixinxiView;


/**
 * 书籍信息章节表
 * 
 * @author 
 * @email 
 * @date 2025-03-27 20:24:07
 */
public interface ChaptershujixinxiDao extends BaseMapper<ChaptershujixinxiEntity> {
	
	List<ChaptershujixinxiVO> selectListVO(@Param("ew") Wrapper<ChaptershujixinxiEntity> wrapper);
	
	ChaptershujixinxiVO selectVO(@Param("ew") Wrapper<ChaptershujixinxiEntity> wrapper);
	
	List<ChaptershujixinxiView> selectListView(@Param("ew") Wrapper<ChaptershujixinxiEntity> wrapper);

	List<ChaptershujixinxiView> selectListView(Pagination page,@Param("ew") Wrapper<ChaptershujixinxiEntity> wrapper);

	
	ChaptershujixinxiView selectView(@Param("ew") Wrapper<ChaptershujixinxiEntity> wrapper);
	

}
