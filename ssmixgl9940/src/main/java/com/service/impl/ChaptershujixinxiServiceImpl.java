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


import com.dao.ChaptershujixinxiDao;
import com.entity.ChaptershujixinxiEntity;
import com.service.ChaptershujixinxiService;
import com.entity.vo.ChaptershujixinxiVO;
import com.entity.view.ChaptershujixinxiView;

@Service("chaptershujixinxiService")
public class ChaptershujixinxiServiceImpl extends ServiceImpl<ChaptershujixinxiDao, ChaptershujixinxiEntity> implements ChaptershujixinxiService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChaptershujixinxiEntity> page = this.selectPage(
                new Query<ChaptershujixinxiEntity>(params).getPage(),
                new EntityWrapper<ChaptershujixinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ChaptershujixinxiEntity> wrapper) {
		  Page<ChaptershujixinxiView> page =new Query<ChaptershujixinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ChaptershujixinxiVO> selectListVO(Wrapper<ChaptershujixinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ChaptershujixinxiVO selectVO(Wrapper<ChaptershujixinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ChaptershujixinxiView> selectListView(Wrapper<ChaptershujixinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ChaptershujixinxiView selectView(Wrapper<ChaptershujixinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
