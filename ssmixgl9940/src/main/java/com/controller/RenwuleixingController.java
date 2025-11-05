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

import com.entity.RenwuleixingEntity;
import com.entity.view.RenwuleixingView;

import com.service.RenwuleixingService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;

/**
 * 任务类型
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-27 20:24:05
 */
@RestController
@RequestMapping("/renwuleixing")
public class RenwuleixingController {
    @Autowired
    private RenwuleixingService renwuleixingService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,RenwuleixingEntity renwuleixing,
		HttpServletRequest request){

        //设置查询条件
        EntityWrapper<RenwuleixingEntity> ew = new EntityWrapper<RenwuleixingEntity>();


        //查询结果
		PageUtils page = renwuleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, renwuleixing), params), params));
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
    public R list(@RequestParam Map<String, Object> params,RenwuleixingEntity renwuleixing,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<RenwuleixingEntity> ew = new EntityWrapper<RenwuleixingEntity>();

        //查询结果
		PageUtils page = renwuleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, renwuleixing), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( RenwuleixingEntity renwuleixing){
       	EntityWrapper<RenwuleixingEntity> ew = new EntityWrapper<RenwuleixingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( renwuleixing, "renwuleixing"));
        return R.ok().put("data", renwuleixingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(RenwuleixingEntity renwuleixing){
        EntityWrapper< RenwuleixingEntity> ew = new EntityWrapper< RenwuleixingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( renwuleixing, "renwuleixing"));
		RenwuleixingView renwuleixingView =  renwuleixingService.selectView(ew);
		return R.ok("查询任务类型成功").put("data", renwuleixingView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        RenwuleixingEntity renwuleixing = renwuleixingService.selectById(id);
        renwuleixing = renwuleixingService.selectView(new EntityWrapper<RenwuleixingEntity>().eq("id", id));
				Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(renwuleixing,deSens);
        return R.ok().put("data", renwuleixing);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        RenwuleixingEntity renwuleixing = renwuleixingService.selectById(id);
        renwuleixing = renwuleixingService.selectView(new EntityWrapper<RenwuleixingEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(renwuleixing,deSens);
        return R.ok().put("data", renwuleixing);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody RenwuleixingEntity renwuleixing, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(renwuleixingService.selectCount(new EntityWrapper<RenwuleixingEntity>().eq("renwuleixing", renwuleixing.getRenwuleixing()))>0) {
            return R.error("任务类型已存在");
        }
        //ValidatorUtils.validateEntity(renwuleixing);

        renwuleixingService.insert(renwuleixing);
        return R.ok().put("data",renwuleixing.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody RenwuleixingEntity renwuleixing, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(renwuleixingService.selectCount(new EntityWrapper<RenwuleixingEntity>().eq("renwuleixing", renwuleixing.getRenwuleixing()))>0) {
            return R.error("任务类型已存在");
        }
    	//ValidatorUtils.validateEntity(renwuleixing);

        renwuleixingService.insert(renwuleixing);
        return R.ok().put("data",renwuleixing.getId());
    }




    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody RenwuleixingEntity renwuleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(renwuleixing);
        //验证字段唯一性，否则返回错误信息
        if(renwuleixingService.selectCount(new EntityWrapper<RenwuleixingEntity>().ne("id", renwuleixing.getId()).eq("renwuleixing", renwuleixing.getRenwuleixing()))>0) {
            return R.error("任务类型已存在");
        }
        //全部更新
        renwuleixingService.updateById(renwuleixing);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        renwuleixingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
