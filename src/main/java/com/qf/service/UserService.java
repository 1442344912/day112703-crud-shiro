package com.qf.service;

import com.qf.Response.UserResponse;
import com.qf.domain.User;

import java.util.List;

public interface UserService {


    UserResponse findAll(Integer size, Integer page);

    User findById(Integer id);

    User saveAndFlush(User user);

    String deleteById(User user);



    public User findUserByUsername(String username);
}
