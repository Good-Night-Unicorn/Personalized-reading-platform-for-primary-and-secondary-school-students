package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.BookmarkshujixinxiDao;
import com.entity.BookmarkshujixinxiEntity;
import com.service.BookmarkshujixinxiService;
import com.entity.vo.BookmarkshujixinxiVO;
import com.entity.view.BookmarkshujixinxiView;

@Service("bookmarkshujixinxiService")
public class BookmarkshujixinxiServiceImpl extends ServiceImpl<BookmarkshujixinxiDao, BookmarkshujixinxiEntity> implements BookmarkshujixinxiService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BookmarkshujixinxiEntity> page = this.selectPage(
                new Query<BookmarkshujixinxiEntity>(params).getPage(),
                new EntityWrapper<BookmarkshujixinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<BookmarkshujixinxiEntity> wrapper) {
		  Page<BookmarkshujixinxiView> page =new Query<BookmarkshujixinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<BookmarkshujixinxiVO> selectListVO(Wrapper<BookmarkshujixinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public BookmarkshujixinxiVO selectVO(Wrapper<BookmarkshujixinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<BookmarkshujixinxiView> selectListView(Wrapper<BookmarkshujixinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public BookmarkshujixinxiView selectView(Wrapper<BookmarkshujixinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
