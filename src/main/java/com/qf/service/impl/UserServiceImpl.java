package com.qf.service.impl;

import com.qf.Response.UserResponse;
import com.qf.domain.User;
import com.qf.repository.UserMapper;
import com.qf.repository.UserRepository;
import com.qf.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResponse findAll(Integer size, Integer page) {
        if (page < 0) {
            page = 0;
        } else {
            page = page - 1;
        }
        Pageable pages = PageRequest.of(page, size);
        Page<User> all = userRepository.findAll(pages);
        List<User> content = all.getContent();
        UserResponse userResponse = new UserResponse();
        userResponse.setList(content);
        userResponse.setTotal(all.getTotalElements());
        userResponse.setPage(all.getTotalPages());
        return userResponse;
    }

    @Override
    public User findById(Integer id) {
        Optional<User> byId = userRepository.findById(id);
        User user = new User();
        if (byId.isPresent()) {
            user = byId.get();
        }
        return user;
    }

    @Override
    public User saveAndFlush(User user) {

        return userRepository.saveAndFlush(user);

    }

    @Override
    public String deleteById(User user) {
        try {
            userRepository.deleteById(user.getId());
            return "success";
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return "fail";
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }


}
