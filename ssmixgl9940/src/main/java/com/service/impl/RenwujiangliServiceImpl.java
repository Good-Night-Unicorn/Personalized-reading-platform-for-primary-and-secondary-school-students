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


import com.dao.RenwujiangliDao;
import com.entity.RenwujiangliEntity;
import com.service.RenwujiangliService;
import com.entity.vo.RenwujiangliVO;
import com.entity.view.RenwujiangliView;

@Service("renwujiangliService")
public class RenwujiangliServiceImpl extends ServiceImpl<RenwujiangliDao, RenwujiangliEntity> implements RenwujiangliService {
	

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<RenwujiangliEntity> page = this.selectPage(
                new Query<RenwujiangliEntity>(params).getPage(),
                new EntityWrapper<RenwujiangliEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<RenwujiangliEntity> wrapper) {
		  Page<RenwujiangliView> page =new Query<RenwujiangliView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<RenwujiangliVO> selectListVO(Wrapper<RenwujiangliEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public RenwujiangliVO selectVO(Wrapper<RenwujiangliEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<RenwujiangliView> selectListView(Wrapper<RenwujiangliEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public RenwujiangliView selectView(Wrapper<RenwujiangliEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
