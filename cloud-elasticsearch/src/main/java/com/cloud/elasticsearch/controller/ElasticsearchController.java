package com.cloud.elasticsearch.controller;

import com.cloud.elasticsearch.index.User;
import com.cloud.elasticsearch.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @author Administrator
 * @description es
 */
@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Controller
@RequestMapping(value = "/es")
public class ElasticsearchController {

    @Autowired
    private IUserService userServiceImpl;

    @GetMapping(value = "/getList")
    @ResponseBody
    public Object getList(@RequestParam("name") String name) {
        return userServiceImpl.getByName(name);
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public Object create(@RequestBody User u) {
        return userServiceImpl.insert(u);
    }
}
