package com.cloud.elasticsearch.service;

import com.cloud.elasticsearch.index.User;

import java.util.List;

/**
 * @author Administrator
 * @description
 */
public interface IUserService {

    public Object getByName(String name);

    public Object insert(User user) ;
}
