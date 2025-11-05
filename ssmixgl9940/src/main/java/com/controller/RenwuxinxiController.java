package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.RenwuxinxiEntity;
import com.entity.view.RenwuxinxiView;

import com.service.RenwuxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;

/**
 * 任务信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-27 20:24:05
 */
@RestController
@RequestMapping("/renwuxinxi")
public class RenwuxinxiController {
    @Autowired
    private RenwuxinxiService renwuxinxiService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,RenwuxinxiEntity renwuxinxi,
		HttpServletRequest request){

        //设置查询条件
        EntityWrapper<RenwuxinxiEntity> ew = new EntityWrapper<RenwuxinxiEntity>();


        //查询结果
		PageUtils page = renwuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, renwuxinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,RenwuxinxiEntity renwuxinxi,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<RenwuxinxiEntity> ew = new EntityWrapper<RenwuxinxiEntity>();

        //查询结果
		PageUtils page = renwuxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, renwuxinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( RenwuxinxiEntity renwuxinxi){
       	EntityWrapper<RenwuxinxiEntity> ew = new EntityWrapper<RenwuxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( renwuxinxi, "renwuxinxi"));
        return R.ok().put("data", renwuxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(RenwuxinxiEntity renwuxinxi){
        EntityWrapper< RenwuxinxiEntity> ew = new EntityWrapper< RenwuxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( renwuxinxi, "renwuxinxi"));
		RenwuxinxiView renwuxinxiView =  renwuxinxiService.selectView(ew);
		return R.ok("查询任务信息成功").put("data", renwuxinxiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        RenwuxinxiEntity renwuxinxi = renwuxinxiService.selectById(id);
        renwuxinxi = renwuxinxiService.selectView(new EntityWrapper<RenwuxinxiEntity>().eq("id", id));
				Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(renwuxinxi,deSens);
        return R.ok().put("data", renwuxinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        RenwuxinxiEntity renwuxinxi = renwuxinxiService.selectById(id);
        renwuxinxi = renwuxinxiService.selectView(new EntityWrapper<RenwuxinxiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(renwuxinxi,deSens);
        return R.ok().put("data", renwuxinxi);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody RenwuxinxiEntity renwuxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(renwuxinxi);

        renwuxinxiService.insert(renwuxinxi);
        return R.ok().put("data",renwuxinxi.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody RenwuxinxiEntity renwuxinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(renwuxinxi);

        renwuxinxiService.insert(renwuxinxi);
        return R.ok().put("data",renwuxinxi.getId());
    }




    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody RenwuxinxiEntity renwuxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(renwuxinxi);
        //全部更新
        renwuxinxiService.updateById(renwuxinxi);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        renwuxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
