package com.itheima.bigevent.controller;

import com.itheima.bigevent.pojo.Result;
import com.itheima.bigevent.pojo.User;
import com.itheima.bigevent.service.UserService;
import com.itheima.bigevent.utils.JwtUtil;
import com.itheima.bigevent.utils.Md5Util;
import com.itheima.bigevent.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/*
用户控制器类
 */
@RestController
@RequestMapping("/user")
@Validated//参数校验，以及下面pattern，@Validated参数
public class UserController {

    @Autowired
    private UserService userService;

    /*
    用户注册
     */
    @PostMapping("/register")
    public Result register(@Pattern(regexp="^\\S{5,16}$") String username,@Pattern(regexp="^\\S{5,16}$")  String password){
        //查询用户
        User u=userService.findByUserName(username);
        if(u==null){
            //没有占用
            //注册
            userService.register(username,password);
            return Result.success();
        }else {
            //占用
            return Result.error("用户名已被占用");
        }
    }

    /*
    用户登录
     */
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp="^\\S{5,16}$") String username,@Pattern(regexp="^\\S{5,16}$")  String password){
        //根据用户名查询用户
        User loginUser = userService.findByUserName(username);
        //判断用户是否存在
        if(loginUser==null){
            return Result.error("用户名错误");
        }

        //判断密码是否正确 loginUser对象中的password是密文
        if(Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            //登录成功
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());

            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }

        return Result.error("密码错误");

    }

    /*
    获取用户详细信息
     */
    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/){
        /*//根据用户名查询用户
        Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String)map.get("username");*/
        Map<String,Object> map = ThreadLocalUtil.get();
        String username =(String) map.get("username");


        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    /*
    更新用户基本信息
    @RequestBody 从前端接收json数据
    @Validated 实体参数校验
     */
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    /*
    更新用户头像
    @URL 实体参数校验
     */
    @PatchMapping("/updateAvatar")
    public Result updateAvater(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    /*
    更新用户密码
    注；这里密码填写并没有设置完整校验
     */
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){
        //1.校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd)|| !StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要的参数");
        }

        //原密码是否正确
        //调用userService根据用户名拿到密码，再和old_pwd对比
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码填写错误");
        }

        //newPwd和rePwd是否一样
        if (!rePwd.equals(newPwd)){
            return Result.error("两次填写的新密码不一样");
        }

        //2.调用service完成密码更新
        userService.updatePwd(newPwd);
        return Result.success();
    }


}
