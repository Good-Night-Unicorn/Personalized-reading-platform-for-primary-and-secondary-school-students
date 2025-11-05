package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.BookmarkshujixinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.BookmarkshujixinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.BookmarkshujixinxiView;


/**
 * 书籍信息书签表
 *
 * @author 
 * @email 
 * @date 2025-03-27 20:24:07
 */
public interface BookmarkshujixinxiService extends IService<BookmarkshujixinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<BookmarkshujixinxiVO> selectListVO(Wrapper<BookmarkshujixinxiEntity> wrapper);
   	
   	BookmarkshujixinxiVO selectVO(@Param("ew") Wrapper<BookmarkshujixinxiEntity> wrapper);
   	
   	List<BookmarkshujixinxiView> selectListView(Wrapper<BookmarkshujixinxiEntity> wrapper);
   	
   	BookmarkshujixinxiView selectView(@Param("ew") Wrapper<BookmarkshujixinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<BookmarkshujixinxiEntity> wrapper);

   	

}

