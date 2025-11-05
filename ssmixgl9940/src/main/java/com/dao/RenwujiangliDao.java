package com.dao;

import com.entity.RenwujiangliEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.RenwujiangliVO;
import com.entity.view.RenwujiangliView;


/**
 * 任务奖励
 * 
 * @author 
 * @email 
 * @date 2025-03-27 20:24:05
 */
public interface RenwujiangliDao extends BaseMapper<RenwujiangliEntity> {
	
	List<RenwujiangliVO> selectListVO(@Param("ew") Wrapper<RenwujiangliEntity> wrapper);
	
	RenwujiangliVO selectVO(@Param("ew") Wrapper<RenwujiangliEntity> wrapper);
	
	List<RenwujiangliView> selectListView(@Param("ew") Wrapper<RenwujiangliEntity> wrapper);

	List<RenwujiangliView> selectListView(Pagination page,@Param("ew") Wrapper<RenwujiangliEntity> wrapper);

	
	RenwujiangliView selectView(@Param("ew") Wrapper<RenwujiangliEntity> wrapper);
	

}
