package com.cloud.elasticsearch.service.impl;

import com.cloud.elasticsearch.index.User;
import com.cloud.elasticsearch.mapper.index.UserRepository;
import com.cloud.elasticsearch.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @description
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public Object getByName(String name) {
        return userRepository.insert(null);
    }

    @Override
    public Object insert(User user) {
        return userRepository.insert(user);
    }
}
