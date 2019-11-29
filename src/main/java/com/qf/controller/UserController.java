package com.qf.controller;

import com.qf.Response.UserResponse;
import com.qf.domain.User;
import com.qf.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserController {
    private static final Logger logger= LogManager.getLogger(UserController.class);


    @Autowired
    private UserService userService;

    @RequestMapping("/findAll/{size}/{page}")
    public UserResponse findAll(@PathVariable("size") Integer size, @PathVariable("page") Integer page) {
        //logger.info("8081被调用了");
        return userService.findAll(size, page);

    }

    @RequestMapping("/findOne")
    public User findOne(@RequestBody User user) {
        Integer id = user.getId();
        return userService.findById(id);
    }

    @RequestMapping("/updateuser")
    public User updateuser(@RequestBody User user) {
        return userService.saveAndFlush(user);
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public String deleteById(@RequestBody User user) {
        return userService.deleteById(user);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String handlLogin(@RequestParam("username")String username,@RequestParam("password")String password){

        if(!StringUtils.isEmpty(username)&&!StringUtils.isEmpty(password)){

            Subject subject =  SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);

        }
        return "redirect:/userlist";
    }
}
