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

import com.entity.TokenEntity;
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

import com.entity.XueshengEntity;
import com.entity.view.XueshengView;

import com.service.XueshengService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;

/**
 * 学生
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-27 20:24:05
 */
@RestController
@RequestMapping("/xuesheng")
public class XueshengController {
    @Autowired
    private XueshengService xueshengService;






	@Autowired
	private TokenService tokenService;

	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
        // 根据登录查询用户信息
        XueshengEntity u = xueshengService.selectOne(new EntityWrapper<XueshengEntity>().eq("xuehao", username));
        // 当用户不存在或验证密码不通过时
		if(u==null || !u.getMima().equals(password)) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
        // 获取登录token
		String token = tokenService.generateToken(u.getId(), username,"xuesheng",  "学生" );
        //返回token
		return R.ok().put("token", token);
	}


	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody XueshengEntity xuesheng){
    	//ValidatorUtils.validateEntity(xuesheng);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	XueshengEntity u = xueshengService.selectOne(new EntityWrapper<XueshengEntity>().eq("xuehao", xuesheng.getXuehao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        if(xueshengService.selectCount(new EntityWrapper<XueshengEntity>().eq("xuehao", xuesheng.getXuehao()))>0) {
            return R.error("学号已存在");
        }
		Long uId = new Date().getTime();
		xuesheng.setId(uId);
        //保存用户
        xueshengService.insert(xuesheng);
        return R.ok();
    }


	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}

	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        XueshengEntity u = xueshengService.selectById(id);
        return R.ok().put("data", u);
    }

    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
        //根据登录账号判断是否存在用户信息，否则返回错误信息
        XueshengEntity u = xueshengService.selectOne(new EntityWrapper<XueshengEntity>().eq("xuehao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456
    	u.setMima("123456");
        xueshengService.updateById(u);
        return R.ok("密码已重置为：123456");
    }



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,XueshengEntity xuesheng,
		HttpServletRequest request){

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiazhang")) {
			xuesheng.setYonghuming((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<XueshengEntity> ew = new EntityWrapper<XueshengEntity>();


        //查询结果
		PageUtils page = xueshengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuesheng), params), params));
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
    public R list(@RequestParam Map<String, Object> params,XueshengEntity xuesheng,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<XueshengEntity> ew = new EntityWrapper<XueshengEntity>();

        //查询结果
		PageUtils page = xueshengService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, xuesheng), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( XueshengEntity xuesheng){
       	EntityWrapper<XueshengEntity> ew = new EntityWrapper<XueshengEntity>();
      	ew.allEq(MPUtil.allEQMapPre( xuesheng, "xuesheng"));
        return R.ok().put("data", xueshengService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(XueshengEntity xuesheng){
        EntityWrapper< XueshengEntity> ew = new EntityWrapper< XueshengEntity>();
 		ew.allEq(MPUtil.allEQMapPre( xuesheng, "xuesheng"));
		XueshengView xueshengView =  xueshengService.selectView(ew);
		return R.ok("查询学生成功").put("data", xueshengView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        XueshengEntity xuesheng = xueshengService.selectById(id);
        xuesheng = xueshengService.selectView(new EntityWrapper<XueshengEntity>().eq("id", id));
				Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xuesheng,deSens);
        return R.ok().put("data", xuesheng);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        XueshengEntity xuesheng = xueshengService.selectById(id);
        xuesheng = xueshengService.selectView(new EntityWrapper<XueshengEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(xuesheng,deSens);
        return R.ok().put("data", xuesheng);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody XueshengEntity xuesheng, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(xueshengService.selectCount(new EntityWrapper<XueshengEntity>().eq("xuehao", xuesheng.getXuehao()))>0) {
            return R.error("学号已存在");
        }
        //ValidatorUtils.validateEntity(xuesheng);
        //验证账号唯一性，否则返回错误信息
        XueshengEntity u = xueshengService.selectOne(new EntityWrapper<XueshengEntity>().eq("xuehao", xuesheng.getXuehao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	xuesheng.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());

		xuesheng.setId(new Date().getTime());
        xueshengService.insert(xuesheng);
        return R.ok().put("data",xuesheng.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody XueshengEntity xuesheng, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(xueshengService.selectCount(new EntityWrapper<XueshengEntity>().eq("xuehao", xuesheng.getXuehao()))>0) {
            return R.error("学号已存在");
        }
    	//ValidatorUtils.validateEntity(xuesheng);
        //验证账号唯一性，否则返回错误信息
    	XueshengEntity u = xueshengService.selectOne(new EntityWrapper<XueshengEntity>().eq("xuehao", xuesheng.getXuehao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
        xuesheng.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());

		xuesheng.setId(new Date().getTime());
        xueshengService.insert(xuesheng);
        return R.ok().put("data",xuesheng.getId());
    }




    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody XueshengEntity xuesheng, HttpServletRequest request){
        //ValidatorUtils.validateEntity(xuesheng);
        //验证字段唯一性，否则返回错误信息
        if(xueshengService.selectCount(new EntityWrapper<XueshengEntity>().ne("id", xuesheng.getId()).eq("xuehao", xuesheng.getXuehao()))>0) {
            return R.error("学号已存在");
        }
        //全部更新
        xueshengService.updateById(xuesheng);
    if(null!=xuesheng.getXuehao()){
        // 修改token
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setUsername(xuesheng.getXuehao());
        tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", xuesheng.getId()));
    }
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        xueshengService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
