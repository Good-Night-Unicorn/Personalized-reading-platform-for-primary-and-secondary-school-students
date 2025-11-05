package com.dao;

import com.entity.RenwutijiaoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.RenwutijiaoVO;
import com.entity.view.RenwutijiaoView;


/**
 * 任务提交
 * 
 * @author 
 * @email 
 * @date 2025-03-27 20:24:05
 */
public interface RenwutijiaoDao extends BaseMapper<RenwutijiaoEntity> {
	
	List<RenwutijiaoVO> selectListVO(@Param("ew") Wrapper<RenwutijiaoEntity> wrapper);
	
	RenwutijiaoVO selectVO(@Param("ew") Wrapper<RenwutijiaoEntity> wrapper);
	
	List<RenwutijiaoView> selectListView(@Param("ew") Wrapper<RenwutijiaoEntity> wrapper);

	List<RenwutijiaoView> selectListView(Pagination page,@Param("ew") Wrapper<RenwutijiaoEntity> wrapper);

	
	RenwutijiaoView selectView(@Param("ew") Wrapper<RenwutijiaoEntity> wrapper);
	

}
