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

import com.entity.RenwujiangliEntity;
import com.entity.view.RenwujiangliView;

import com.service.RenwujiangliService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;

/**
 * 任务奖励
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-27 20:24:05
 */
@RestController
@RequestMapping("/renwujiangli")
public class RenwujiangliController {
    @Autowired
    private RenwujiangliService renwujiangliService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,RenwujiangliEntity renwujiangli,
		HttpServletRequest request){

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("xuesheng")) {
			renwujiangli.setXuehao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("jiazhang")) {
			renwujiangli.setYonghuming((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<RenwujiangliEntity> ew = new EntityWrapper<RenwujiangliEntity>();


        //查询结果
		PageUtils page = renwujiangliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, renwujiangli), params), params));
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
    public R list(@RequestParam Map<String, Object> params,RenwujiangliEntity renwujiangli,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<RenwujiangliEntity> ew = new EntityWrapper<RenwujiangliEntity>();

        //查询结果
		PageUtils page = renwujiangliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, renwujiangli), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( RenwujiangliEntity renwujiangli){
       	EntityWrapper<RenwujiangliEntity> ew = new EntityWrapper<RenwujiangliEntity>();
      	ew.allEq(MPUtil.allEQMapPre( renwujiangli, "renwujiangli"));
        return R.ok().put("data", renwujiangliService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(RenwujiangliEntity renwujiangli){
        EntityWrapper< RenwujiangliEntity> ew = new EntityWrapper< RenwujiangliEntity>();
 		ew.allEq(MPUtil.allEQMapPre( renwujiangli, "renwujiangli"));
		RenwujiangliView renwujiangliView =  renwujiangliService.selectView(ew);
		return R.ok("查询任务奖励成功").put("data", renwujiangliView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        RenwujiangliEntity renwujiangli = renwujiangliService.selectById(id);
        renwujiangli = renwujiangliService.selectView(new EntityWrapper<RenwujiangliEntity>().eq("id", id));
				Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(renwujiangli,deSens);
        return R.ok().put("data", renwujiangli);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        RenwujiangliEntity renwujiangli = renwujiangliService.selectById(id);
        renwujiangli = renwujiangliService.selectView(new EntityWrapper<RenwujiangliEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(renwujiangli,deSens);
        return R.ok().put("data", renwujiangli);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody RenwujiangliEntity renwujiangli, HttpServletRequest request){
        //ValidatorUtils.validateEntity(renwujiangli);

        renwujiangliService.insert(renwujiangli);
        return R.ok().put("data",renwujiangli.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody RenwujiangliEntity renwujiangli, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(renwujiangli);

        renwujiangliService.insert(renwujiangli);
        return R.ok().put("data",renwujiangli.getId());
    }




    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody RenwujiangliEntity renwujiangli, HttpServletRequest request){
        //ValidatorUtils.validateEntity(renwujiangli);
        //全部更新
        renwujiangliService.updateById(renwujiangli);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        renwujiangliService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
