package com.qf.repository;

import com.qf.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {
    public User findUserByUsername(String username);
}
