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

import com.entity.RenwutijiaoEntity;
import com.entity.view.RenwutijiaoView;

import com.service.RenwutijiaoService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;

/**
 * 任务提交
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-27 20:24:05
 */
@RestController
@RequestMapping("/renwutijiao")
public class RenwutijiaoController {
    @Autowired
    private RenwutijiaoService renwutijiaoService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,RenwutijiaoEntity renwutijiao,
		HttpServletRequest request){

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("xuesheng")) {
			renwutijiao.setXuehao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("jiazhang")) {
			renwutijiao.setYonghuming((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<RenwutijiaoEntity> ew = new EntityWrapper<RenwutijiaoEntity>();


        //查询结果
		PageUtils page = renwutijiaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, renwutijiao), params), params));
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
    public R list(@RequestParam Map<String, Object> params,RenwutijiaoEntity renwutijiao,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<RenwutijiaoEntity> ew = new EntityWrapper<RenwutijiaoEntity>();

        //查询结果
		PageUtils page = renwutijiaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, renwutijiao), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( RenwutijiaoEntity renwutijiao){
       	EntityWrapper<RenwutijiaoEntity> ew = new EntityWrapper<RenwutijiaoEntity>();
      	ew.allEq(MPUtil.allEQMapPre( renwutijiao, "renwutijiao"));
        return R.ok().put("data", renwutijiaoService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(RenwutijiaoEntity renwutijiao){
        EntityWrapper< RenwutijiaoEntity> ew = new EntityWrapper< RenwutijiaoEntity>();
 		ew.allEq(MPUtil.allEQMapPre( renwutijiao, "renwutijiao"));
		RenwutijiaoView renwutijiaoView =  renwutijiaoService.selectView(ew);
		return R.ok("查询任务提交成功").put("data", renwutijiaoView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        RenwutijiaoEntity renwutijiao = renwutijiaoService.selectById(id);
        renwutijiao = renwutijiaoService.selectView(new EntityWrapper<RenwutijiaoEntity>().eq("id", id));
				Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(renwutijiao,deSens);
        return R.ok().put("data", renwutijiao);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        RenwutijiaoEntity renwutijiao = renwutijiaoService.selectById(id);
        renwutijiao = renwutijiaoService.selectView(new EntityWrapper<RenwutijiaoEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(renwutijiao,deSens);
        return R.ok().put("data", renwutijiao);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody RenwutijiaoEntity renwutijiao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(renwutijiao);

        renwutijiaoService.insert(renwutijiao);
        return R.ok().put("data",renwutijiao.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody RenwutijiaoEntity renwutijiao, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(renwutijiao);

        renwutijiaoService.insert(renwutijiao);
        return R.ok().put("data",renwutijiao.getId());
    }




    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody RenwutijiaoEntity renwutijiao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(renwutijiao);
        //全部更新
        renwutijiaoService.updateById(renwutijiao);
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<RenwutijiaoEntity> list = new ArrayList<RenwutijiaoEntity>();
        for(Long id : ids) {
            RenwutijiaoEntity renwutijiao = renwutijiaoService.selectById(id);
            renwutijiao.setSfsh(sfsh);
            renwutijiao.setShhf(shhf);
            list.add(renwutijiao);
        }
        renwutijiaoService.updateBatchById(list);
        return R.ok();
    }



    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        renwutijiaoService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
