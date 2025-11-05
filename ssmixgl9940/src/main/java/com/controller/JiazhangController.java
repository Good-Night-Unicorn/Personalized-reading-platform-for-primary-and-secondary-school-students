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

import com.entity.JiazhangEntity;
import com.entity.view.JiazhangView;

import com.service.JiazhangService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;

/**
 * 家长
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-27 20:24:04
 */
@RestController
@RequestMapping("/jiazhang")
public class JiazhangController {
    @Autowired
    private JiazhangService jiazhangService;






	@Autowired
	private TokenService tokenService;

	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
        // 根据登录查询用户信息
        JiazhangEntity u = jiazhangService.selectOne(new EntityWrapper<JiazhangEntity>().eq("yonghuming", username));
        // 当用户不存在或验证密码不通过时
		if(u==null || !u.getMima().equals(password)) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
        // 获取登录token
		String token = tokenService.generateToken(u.getId(), username,"jiazhang",  "家长" );
        //返回token
		return R.ok().put("token", token);
	}


	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody JiazhangEntity jiazhang){
    	//ValidatorUtils.validateEntity(jiazhang);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	JiazhangEntity u = jiazhangService.selectOne(new EntityWrapper<JiazhangEntity>().eq("yonghuming", jiazhang.getYonghuming()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        if(jiazhangService.selectCount(new EntityWrapper<JiazhangEntity>().eq("yonghuming", jiazhang.getYonghuming()))>0) {
            return R.error("用户名已存在");
        }
		Long uId = new Date().getTime();
		jiazhang.setId(uId);
        //保存用户
        jiazhangService.insert(jiazhang);
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
        JiazhangEntity u = jiazhangService.selectById(id);
        return R.ok().put("data", u);
    }

    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
        //根据登录账号判断是否存在用户信息，否则返回错误信息
        JiazhangEntity u = jiazhangService.selectOne(new EntityWrapper<JiazhangEntity>().eq("yonghuming", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456
    	u.setMima("123456");
        jiazhangService.updateById(u);
        return R.ok("密码已重置为：123456");
    }



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiazhangEntity jiazhang,
		HttpServletRequest request){

        //设置查询条件
        EntityWrapper<JiazhangEntity> ew = new EntityWrapper<JiazhangEntity>();


        //查询结果
		PageUtils page = jiazhangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiazhang), params), params));
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
    public R list(@RequestParam Map<String, Object> params,JiazhangEntity jiazhang,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<JiazhangEntity> ew = new EntityWrapper<JiazhangEntity>();

        //查询结果
		PageUtils page = jiazhangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiazhang), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiazhangEntity jiazhang){
       	EntityWrapper<JiazhangEntity> ew = new EntityWrapper<JiazhangEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiazhang, "jiazhang"));
        return R.ok().put("data", jiazhangService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiazhangEntity jiazhang){
        EntityWrapper< JiazhangEntity> ew = new EntityWrapper< JiazhangEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiazhang, "jiazhang"));
		JiazhangView jiazhangView =  jiazhangService.selectView(ew);
		return R.ok("查询家长成功").put("data", jiazhangView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiazhangEntity jiazhang = jiazhangService.selectById(id);
        jiazhang = jiazhangService.selectView(new EntityWrapper<JiazhangEntity>().eq("id", id));
				Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jiazhang,deSens);
        return R.ok().put("data", jiazhang);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiazhangEntity jiazhang = jiazhangService.selectById(id);
        jiazhang = jiazhangService.selectView(new EntityWrapper<JiazhangEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jiazhang,deSens);
        return R.ok().put("data", jiazhang);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JiazhangEntity jiazhang, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(jiazhangService.selectCount(new EntityWrapper<JiazhangEntity>().eq("yonghuming", jiazhang.getYonghuming()))>0) {
            return R.error("用户名已存在");
        }
        //ValidatorUtils.validateEntity(jiazhang);
        //验证账号唯一性，否则返回错误信息
        JiazhangEntity u = jiazhangService.selectOne(new EntityWrapper<JiazhangEntity>().eq("yonghuming", jiazhang.getYonghuming()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	jiazhang.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());

		jiazhang.setId(new Date().getTime());
        jiazhangService.insert(jiazhang);
        return R.ok().put("data",jiazhang.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JiazhangEntity jiazhang, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(jiazhangService.selectCount(new EntityWrapper<JiazhangEntity>().eq("yonghuming", jiazhang.getYonghuming()))>0) {
            return R.error("用户名已存在");
        }
    	//ValidatorUtils.validateEntity(jiazhang);
        //验证账号唯一性，否则返回错误信息
    	JiazhangEntity u = jiazhangService.selectOne(new EntityWrapper<JiazhangEntity>().eq("yonghuming", jiazhang.getYonghuming()));
		if(u!=null) {
			return R.error("用户已存在");
		}
        jiazhang.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());

		jiazhang.setId(new Date().getTime());
        jiazhangService.insert(jiazhang);
        return R.ok().put("data",jiazhang.getId());
    }




    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JiazhangEntity jiazhang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiazhang);
        //验证字段唯一性，否则返回错误信息
        if(jiazhangService.selectCount(new EntityWrapper<JiazhangEntity>().ne("id", jiazhang.getId()).eq("yonghuming", jiazhang.getYonghuming()))>0) {
            return R.error("用户名已存在");
        }
        //全部更新
        jiazhangService.updateById(jiazhang);
    if(null!=jiazhang.getYonghuming()){
        // 修改token
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setUsername(jiazhang.getYonghuming());
        tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", jiazhang.getId()));
    }
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jiazhangService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
