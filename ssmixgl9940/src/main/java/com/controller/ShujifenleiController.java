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

import com.entity.ShujifenleiEntity;
import com.entity.view.ShujifenleiView;

import com.service.ShujifenleiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;

/**
 * 书籍分类
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-27 20:24:05
 */
@RestController
@RequestMapping("/shujifenlei")
public class ShujifenleiController {
    @Autowired
    private ShujifenleiService shujifenleiService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ShujifenleiEntity shujifenlei,
		HttpServletRequest request){

        //设置查询条件
        EntityWrapper<ShujifenleiEntity> ew = new EntityWrapper<ShujifenleiEntity>();


        //查询结果
		PageUtils page = shujifenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shujifenlei), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ShujifenleiEntity shujifenlei,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ShujifenleiEntity> ew = new EntityWrapper<ShujifenleiEntity>();

        //查询结果
		PageUtils page = shujifenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shujifenlei), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ShujifenleiEntity shujifenlei){
       	EntityWrapper<ShujifenleiEntity> ew = new EntityWrapper<ShujifenleiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( shujifenlei, "shujifenlei"));
        return R.ok().put("data", shujifenleiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ShujifenleiEntity shujifenlei){
        EntityWrapper< ShujifenleiEntity> ew = new EntityWrapper< ShujifenleiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( shujifenlei, "shujifenlei"));
		ShujifenleiView shujifenleiView =  shujifenleiService.selectView(ew);
		return R.ok("查询书籍分类成功").put("data", shujifenleiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ShujifenleiEntity shujifenlei = shujifenleiService.selectById(id);
        shujifenlei = shujifenleiService.selectView(new EntityWrapper<ShujifenleiEntity>().eq("id", id));
				Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(shujifenlei,deSens);
        return R.ok().put("data", shujifenlei);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ShujifenleiEntity shujifenlei = shujifenleiService.selectById(id);
        shujifenlei = shujifenleiService.selectView(new EntityWrapper<ShujifenleiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(shujifenlei,deSens);
        return R.ok().put("data", shujifenlei);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ShujifenleiEntity shujifenlei, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(shujifenleiService.selectCount(new EntityWrapper<ShujifenleiEntity>().eq("shujifenlei", shujifenlei.getShujifenlei()))>0) {
            return R.error("书籍分类已存在");
        }
        //ValidatorUtils.validateEntity(shujifenlei);

        shujifenleiService.insert(shujifenlei);
        return R.ok().put("data",shujifenlei.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ShujifenleiEntity shujifenlei, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(shujifenleiService.selectCount(new EntityWrapper<ShujifenleiEntity>().eq("shujifenlei", shujifenlei.getShujifenlei()))>0) {
            return R.error("书籍分类已存在");
        }
    	//ValidatorUtils.validateEntity(shujifenlei);

        shujifenleiService.insert(shujifenlei);
        return R.ok().put("data",shujifenlei.getId());
    }




    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ShujifenleiEntity shujifenlei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shujifenlei);
        //验证字段唯一性，否则返回错误信息
        if(shujifenleiService.selectCount(new EntityWrapper<ShujifenleiEntity>().ne("id", shujifenlei.getId()).eq("shujifenlei", shujifenlei.getShujifenlei()))>0) {
            return R.error("书籍分类已存在");
        }
        //全部更新
        shujifenleiService.updateById(shujifenlei);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        shujifenleiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
