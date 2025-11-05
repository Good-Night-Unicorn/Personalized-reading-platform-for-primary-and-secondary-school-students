package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.RenwujiangliEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.RenwujiangliVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.RenwujiangliView;


/**
 * 任务奖励
 *
 * @author 
 * @email 
 * @date 2025-03-27 20:24:05
 */
public interface RenwujiangliService extends IService<RenwujiangliEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<RenwujiangliVO> selectListVO(Wrapper<RenwujiangliEntity> wrapper);
   	
   	RenwujiangliVO selectVO(@Param("ew") Wrapper<RenwujiangliEntity> wrapper);
   	
   	List<RenwujiangliView> selectListView(Wrapper<RenwujiangliEntity> wrapper);
   	
   	RenwujiangliView selectView(@Param("ew") Wrapper<RenwujiangliEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<RenwujiangliEntity> wrapper);

   	

}

