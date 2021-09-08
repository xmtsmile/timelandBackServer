package com.study.note.service;

import com.study.note.entity.User;
import com.study.note.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     *
     */
    public String login(User user)
    {
        User oldUser = userMapper.findByName(user.getUserName());
        if(oldUser != null){
            if (oldUser.getPassword().equals(user.getPassword())){
                return "登录成功,欢迎您！";
            }else {
                return "登陆失败,密码错误！";
            }
        }else{
            return "登录失败，账户不存在！";
        }
    }

    /**
     * 注册
     *
     */
    public String regist(User user)
    {
        String name = user.getUserName();
        User oldUser = userMapper.findByName(name);
        if (user.getUserName().equals("")){
            return "账户名不能为空";
        }else if (user.getPassword().equals("")){
            return "密码不能为空";
        }else if (oldUser != null){
            return "账户已经存在";
        }else {
            userMapper.insert(user);
            return "注册成功";
        }
    }

    /**
     * 保存微信小程序注册信息
     *
     */
    public void wxlogin(User user)
    {
        userMapper.insert(user);
    }
}
