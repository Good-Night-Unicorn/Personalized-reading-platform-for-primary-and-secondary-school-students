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

import com.entity.BookmarkshujixinxiEntity;
import com.entity.view.BookmarkshujixinxiView;

import com.service.BookmarkshujixinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;

/**
 * 书籍信息书签表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-27 20:24:07
 */
@RestController
@RequestMapping("/bookmarkshujixinxi")
public class BookmarkshujixinxiController {
    @Autowired
    private BookmarkshujixinxiService bookmarkshujixinxiService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,BookmarkshujixinxiEntity bookmarkshujixinxi,
		HttpServletRequest request){

        //设置查询条件
        EntityWrapper<BookmarkshujixinxiEntity> ew = new EntityWrapper<BookmarkshujixinxiEntity>();


        //查询结果
		PageUtils page = bookmarkshujixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, bookmarkshujixinxi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,BookmarkshujixinxiEntity bookmarkshujixinxi,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<BookmarkshujixinxiEntity> ew = new EntityWrapper<BookmarkshujixinxiEntity>();

        //查询结果
		PageUtils page = bookmarkshujixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, bookmarkshujixinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( BookmarkshujixinxiEntity bookmarkshujixinxi){
       	EntityWrapper<BookmarkshujixinxiEntity> ew = new EntityWrapper<BookmarkshujixinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( bookmarkshujixinxi, "bookmarkshujixinxi"));
        return R.ok().put("data", bookmarkshujixinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(BookmarkshujixinxiEntity bookmarkshujixinxi){
        EntityWrapper< BookmarkshujixinxiEntity> ew = new EntityWrapper< BookmarkshujixinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( bookmarkshujixinxi, "bookmarkshujixinxi"));
		BookmarkshujixinxiView bookmarkshujixinxiView =  bookmarkshujixinxiService.selectView(ew);
		return R.ok("查询书籍信息书签表成功").put("data", bookmarkshujixinxiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        BookmarkshujixinxiEntity bookmarkshujixinxi = bookmarkshujixinxiService.selectById(id);
        bookmarkshujixinxi = bookmarkshujixinxiService.selectView(new EntityWrapper<BookmarkshujixinxiEntity>().eq("id", id));
				Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(bookmarkshujixinxi,deSens);
        return R.ok().put("data", bookmarkshujixinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        BookmarkshujixinxiEntity bookmarkshujixinxi = bookmarkshujixinxiService.selectById(id);
        bookmarkshujixinxi = bookmarkshujixinxiService.selectView(new EntityWrapper<BookmarkshujixinxiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(bookmarkshujixinxi,deSens);
        return R.ok().put("data", bookmarkshujixinxi);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BookmarkshujixinxiEntity bookmarkshujixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(bookmarkshujixinxi);

        bookmarkshujixinxiService.insert(bookmarkshujixinxi);
        return R.ok().put("data",bookmarkshujixinxi.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody BookmarkshujixinxiEntity bookmarkshujixinxi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(bookmarkshujixinxi);

        bookmarkshujixinxiService.insert(bookmarkshujixinxi);
        return R.ok().put("data",bookmarkshujixinxi.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        BookmarkshujixinxiEntity bookmarkshujixinxi = bookmarkshujixinxiService.selectOne(new EntityWrapper<BookmarkshujixinxiEntity>().eq("", username));
        return R.ok().put("data", bookmarkshujixinxi);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody BookmarkshujixinxiEntity bookmarkshujixinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(bookmarkshujixinxi);
        //全部更新
        bookmarkshujixinxiService.updateById(bookmarkshujixinxi);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        bookmarkshujixinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,BookmarkshujixinxiEntity bookmarkshujixinxi, HttpServletRequest request,String pre){
        EntityWrapper<BookmarkshujixinxiEntity> ew = new EntityWrapper<BookmarkshujixinxiEntity>();
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
		PageUtils page = bookmarkshujixinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, bookmarkshujixinxi), params), params));
        return R.ok().put("data", page);
    }









}
