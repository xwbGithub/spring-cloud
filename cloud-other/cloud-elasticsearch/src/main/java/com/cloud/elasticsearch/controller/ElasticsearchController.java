package com.cloud.elasticsearch.controller;

import com.cloud.elasticsearch.service.ElasticsearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author Administrator
 * @description es
 */
@Controller
@RequestMapping(value = "/index")
public class ElasticsearchController {

    @Resource
    ElasticsearchService service;


    @GetMapping(value = "/insert")
    @ResponseBody
    public Object getList(@RequestBody Object o) {
        return service.insert(o);
    }

    @RequestMapping(value = "/insert")
    @ResponseBody
    public Object getRequestList(@RequestBody Object o) {
        return service.insert(o);
    }

    @PostMapping(value = "/query")
    @ResponseBody
    public Object query(@RequestBody Object o) {
        return service.query(o);
    }
}
