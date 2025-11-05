package com.dao;

import com.entity.ShujixinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ShujixinxiVO;
import com.entity.view.ShujixinxiView;


/**
 * 书籍信息
 * 
 * @author 
 * @email 
 * @date 2025-03-27 20:24:05
 */
public interface ShujixinxiDao extends BaseMapper<ShujixinxiEntity> {
	
	List<ShujixinxiVO> selectListVO(@Param("ew") Wrapper<ShujixinxiEntity> wrapper);
	
	ShujixinxiVO selectVO(@Param("ew") Wrapper<ShujixinxiEntity> wrapper);
	
	List<ShujixinxiView> selectListView(@Param("ew") Wrapper<ShujixinxiEntity> wrapper);

	List<ShujixinxiView> selectListView(Pagination page,@Param("ew") Wrapper<ShujixinxiEntity> wrapper);

	
	ShujixinxiView selectView(@Param("ew") Wrapper<ShujixinxiEntity> wrapper);
	

}
