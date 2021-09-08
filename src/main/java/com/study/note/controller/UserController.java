package com.study.note.controller;

import com.study.note.entity.JsonData;
import com.study.note.entity.User;
import com.study.note.service.UserService;
import com.study.note.utils.token.TokenHelper;
import com.study.note.utils.token.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenHelper tokenHelper;

    @PostMapping("/login")
    public Object login(@RequestBody User user){
        TokenModel model = tokenHelper.create(user.getId());
        return JsonData.buildSuccess(model);
    }

    @PostMapping("/regist")
    public String regist(@RequestBody User user){
        return userService.regist(user);
    }

//    /**
//     * 跳转到登录
//     *
//     */
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String login() {
//            return "/login.html";
//    }


}
