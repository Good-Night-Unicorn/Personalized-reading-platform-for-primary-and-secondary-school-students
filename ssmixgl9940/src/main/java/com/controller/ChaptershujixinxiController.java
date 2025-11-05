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

import com.entity.ChaptershujixinxiEntity;
import com.entity.view.ChaptershujixinxiView;

import com.service.ChaptershujixinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;

/**
 * 书籍信息章节表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-27 20:24:07
 */
@RestController
@RequestMapping("/chaptershujixinxi")
public class ChaptershujixinxiController {
    @Autowired
    private ChaptershujixinxiService chaptershujixinxiService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChaptershujixinxiEntity chaptershujixinxi,
		HttpServletRequest request){

        //设置查询条件
        EntityWrapper<ChaptershujixinxiEntity> ew = new EntityWrapper<ChaptershujixinxiEntity>();


        //查询结果
		PageUtils page = chaptershujixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chaptershujixinxi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ChaptershujixinxiEntity chaptershujixinxi,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ChaptershujixinxiEntity> ew = new EntityWrapper<ChaptershujixinxiEntity>();

        //查询结果
		PageUtils page = chaptershujixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chaptershujixinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChaptershujixinxiEntity chaptershujixinxi){
       	EntityWrapper<ChaptershujixinxiEntity> ew = new EntityWrapper<ChaptershujixinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( chaptershujixinxi, "chaptershujixinxi"));
        return R.ok().put("data", chaptershujixinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChaptershujixinxiEntity chaptershujixinxi){
        EntityWrapper< ChaptershujixinxiEntity> ew = new EntityWrapper< ChaptershujixinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( chaptershujixinxi, "chaptershujixinxi"));
		ChaptershujixinxiView chaptershujixinxiView =  chaptershujixinxiService.selectView(ew);
		return R.ok("查询书籍信息章节表成功").put("data", chaptershujixinxiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChaptershujixinxiEntity chaptershujixinxi = chaptershujixinxiService.selectById(id);
        chaptershujixinxi = chaptershujixinxiService.selectView(new EntityWrapper<ChaptershujixinxiEntity>().eq("id", id));
				Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(chaptershujixinxi,deSens);
        return R.ok().put("data", chaptershujixinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChaptershujixinxiEntity chaptershujixinxi = chaptershujixinxiService.selectById(id);
        chaptershujixinxi = chaptershujixinxiService.selectView(new EntityWrapper<ChaptershujixinxiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(chaptershujixinxi,deSens);
        return R.ok().put("data", chaptershujixinxi);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ChaptershujixinxiEntity chaptershujixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chaptershujixinxi);

        chaptershujixinxiService.insert(chaptershujixinxi);
        return R.ok().put("data",chaptershujixinxi.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ChaptershujixinxiEntity chaptershujixinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(chaptershujixinxi);

        chaptershujixinxiService.insert(chaptershujixinxi);
        return R.ok().put("data",chaptershujixinxi.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        ChaptershujixinxiEntity chaptershujixinxi = chaptershujixinxiService.selectOne(new EntityWrapper<ChaptershujixinxiEntity>().eq("", username));
        return R.ok().put("data", chaptershujixinxi);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody ChaptershujixinxiEntity chaptershujixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chaptershujixinxi);
        //全部更新
        chaptershujixinxiService.updateById(chaptershujixinxi);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        chaptershujixinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,ChaptershujixinxiEntity chaptershujixinxi, HttpServletRequest request,String pre){
        EntityWrapper<ChaptershujixinxiEntity> ew = new EntityWrapper<ChaptershujixinxiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        // 组装参数
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");

        params.put("order", "desc");
		PageUtils page = chaptershujixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chaptershujixinxi), params), params));
        return R.ok().put("data", page);
    }









}
