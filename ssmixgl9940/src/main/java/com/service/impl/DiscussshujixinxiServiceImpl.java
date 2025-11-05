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


import com.dao.DiscussshujixinxiDao;
import com.entity.DiscussshujixinxiEntity;
import com.service.DiscussshujixinxiService;
import com.entity.vo.DiscussshujixinxiVO;
import com.entity.view.DiscussshujixinxiView;

@Service("discussshujixinxiService")
public class DiscussshujixinxiServiceImpl extends ServiceImpl<DiscussshujixinxiDao, DiscussshujixinxiEntity> implements DiscussshujixinxiService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussshujixinxiEntity> page = this.selectPage(
                new Query<DiscussshujixinxiEntity>(params).getPage(),
                new EntityWrapper<DiscussshujixinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussshujixinxiEntity> wrapper) {
		  Page<DiscussshujixinxiView> page =new Query<DiscussshujixinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscussshujixinxiVO> selectListVO(Wrapper<DiscussshujixinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussshujixinxiVO selectVO(Wrapper<DiscussshujixinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussshujixinxiView> selectListView(Wrapper<DiscussshujixinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussshujixinxiView selectView(Wrapper<DiscussshujixinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
