package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ChaptershujixinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ChaptershujixinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ChaptershujixinxiView;


/**
 * 书籍信息章节表
 *
 * @author 
 * @email 
 * @date 2025-03-27 20:24:07
 */
public interface ChaptershujixinxiService extends IService<ChaptershujixinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ChaptershujixinxiVO> selectListVO(Wrapper<ChaptershujixinxiEntity> wrapper);
   	
   	ChaptershujixinxiVO selectVO(@Param("ew") Wrapper<ChaptershujixinxiEntity> wrapper);
   	
   	List<ChaptershujixinxiView> selectListView(Wrapper<ChaptershujixinxiEntity> wrapper);
   	
   	ChaptershujixinxiView selectView(@Param("ew") Wrapper<ChaptershujixinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ChaptershujixinxiEntity> wrapper);

   	

}

