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

import com.entity.PopupremindEntity;
import com.entity.view.PopupremindView;

import com.service.PopupremindService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;

/**
 * 弹窗提醒
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-27 20:24:06
 */
@RestController
@RequestMapping("/popupremind")
public class PopupremindController {
    @Autowired
    private PopupremindService popupremindService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,PopupremindEntity popupremind,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date remindtimestart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date remindtimeend,
		HttpServletRequest request){

    	if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
    		popupremind.setUserid((Long)request.getSession().getAttribute("userId"));
    	}
        //设置查询条件
        EntityWrapper<PopupremindEntity> ew = new EntityWrapper<PopupremindEntity>();
        if(remindtimestart!=null) ew.ge("remindtime", remindtimestart);
        if(remindtimeend!=null) ew.le("remindtime", remindtimeend);


        //查询结果
		PageUtils page = popupremindService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, popupremind), params), params));
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
    public R list(@RequestParam Map<String, Object> params,PopupremindEntity popupremind,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date remindtimestart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date remindtimeend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<PopupremindEntity> ew = new EntityWrapper<PopupremindEntity>();
        if(remindtimestart!=null) ew.ge("remindtime", remindtimestart);
        if(remindtimeend!=null) ew.le("remindtime", remindtimeend);

        //查询结果
		PageUtils page = popupremindService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, popupremind), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( PopupremindEntity popupremind){
       	EntityWrapper<PopupremindEntity> ew = new EntityWrapper<PopupremindEntity>();
      	ew.allEq(MPUtil.allEQMapPre( popupremind, "popupremind"));
        return R.ok().put("data", popupremindService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(PopupremindEntity popupremind){
        EntityWrapper< PopupremindEntity> ew = new EntityWrapper< PopupremindEntity>();
 		ew.allEq(MPUtil.allEQMapPre( popupremind, "popupremind"));
		PopupremindView popupremindView =  popupremindService.selectView(ew);
		return R.ok("查询弹窗提醒成功").put("data", popupremindView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        PopupremindEntity popupremind = popupremindService.selectById(id);
        popupremind = popupremindService.selectView(new EntityWrapper<PopupremindEntity>().eq("id", id));
				Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(popupremind,deSens);
        return R.ok().put("data", popupremind);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        PopupremindEntity popupremind = popupremindService.selectById(id);
        popupremind = popupremindService.selectView(new EntityWrapper<PopupremindEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(popupremind,deSens);
        return R.ok().put("data", popupremind);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PopupremindEntity popupremind, HttpServletRequest request){
        //ValidatorUtils.validateEntity(popupremind);
    	popupremind.setUserid((Long)request.getSession().getAttribute("userId"));

        popupremindService.insert(popupremind);
        return R.ok().put("data",popupremind.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody PopupremindEntity popupremind, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(popupremind);

        popupremindService.insert(popupremind);
        return R.ok().put("data",popupremind.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        PopupremindEntity popupremind = popupremindService.selectOne(new EntityWrapper<PopupremindEntity>().eq("", username));
        return R.ok().put("data", popupremind);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody PopupremindEntity popupremind, HttpServletRequest request){
        //ValidatorUtils.validateEntity(popupremind);
        //全部更新
        popupremindService.updateById(popupremind);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        popupremindService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,PopupremindEntity popupremind, HttpServletRequest request,String pre){
        EntityWrapper<PopupremindEntity> ew = new EntityWrapper<PopupremindEntity>();
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
		PageUtils page = popupremindService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, popupremind), params), params));
        return R.ok().put("data", page);
    }









}
