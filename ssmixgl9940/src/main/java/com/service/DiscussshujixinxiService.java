package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussshujixinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussshujixinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussshujixinxiView;


/**
 * 书籍信息评论表
 *
 * @author 
 * @email 
 * @date 2025-03-27 20:24:06
 */
public interface DiscussshujixinxiService extends IService<DiscussshujixinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussshujixinxiVO> selectListVO(Wrapper<DiscussshujixinxiEntity> wrapper);
   	
   	DiscussshujixinxiVO selectVO(@Param("ew") Wrapper<DiscussshujixinxiEntity> wrapper);
   	
   	List<DiscussshujixinxiView> selectListView(Wrapper<DiscussshujixinxiEntity> wrapper);
   	
   	DiscussshujixinxiView selectView(@Param("ew") Wrapper<DiscussshujixinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussshujixinxiEntity> wrapper);

   	

}

