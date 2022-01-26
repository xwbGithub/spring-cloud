package com.cloud.elasticsearch.controller;

import com.cloud.elasticsearch.entity.User;
import com.cloud.elasticsearch.service.ElasticsearchUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


/**
 * @author Administrator
 * @description es
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    ElasticsearchUserService service;


    @PostMapping(value = "/insert")
    @ResponseBody
    public Object getList(@RequestBody User o) {
        return service.insert(o);
    }

    @PostMapping(value = "/insertBatch")
    @ResponseBody
    public Object insertBatch(@RequestBody User o) {
        return service.batchInsert(o);
    }

    @PostMapping(value = "/batchInsertCity")
    @ResponseBody
    public Object batchInsertCity(@RequestBody User o) {
        return service.batchInsertCity(o);
    }

    @PostMapping(value = "/query")
    @ResponseBody
    public Object query(@RequestBody User o) {
        return service.query(o);
    }

    @PostMapping(value = "/queryTerm")
    @ResponseBody
    public Object queryTerm(@RequestBody User o) {
        return service.queryTerm(o);
    }

    @PostMapping(value = "/queryTermOrder")
    @ResponseBody
    public Object queryTermOrder(@RequestBody User o) {
        return service.queryTermOrder(o);
    }

    @PostMapping(value = "/queryFuzzyQuery")
    @ResponseBody
    public Object queryWildcardQueryBuilder(@RequestBody User o) {
        return service.queryFuzzyQuery(o);
    }

    @PostMapping(value = "/queryFieldIncludeExclude")
    @ResponseBody
    public Object queryFieldIncludeExclude(@RequestBody User o) {
        return service.queryFieldIncludeExclude(o);
    }

    @PostMapping(value = "/queryTermCompose")
    @ResponseBody
    public Object queryTermCompose(@RequestBody User o) {
        return service.queryTermCompose(o);
    }

    @PostMapping(value = "/queryTermRange")
    @ResponseBody
    public Object queryTermRange(@RequestBody User o) {
        return service.queryTermRange(o);
    }

    @PostMapping(value = "/queryTermFuzzy")
    @ResponseBody
    public Object queryTermFuzzy(@RequestBody User o) {
        return service.queryTermFuzzy(o);
    }

    @PostMapping(value = "/queryTermHighLight")
    @ResponseBody
    public Object queryTermHighLight(@RequestBody User o) {
        return service.queryTermHighLight(o);
    }

    @PostMapping(value = "/queryAggregationBuilder")
    @ResponseBody
    public Object queryAggregationBuilder(@RequestBody User o) {
        return service.queryAggregationBuilder(o);
    }

    @PostMapping(value = "/queryParticipleHighLight")
    @ResponseBody
    public Object queryParticipleHighLight(@RequestBody User o) {
        return service.queryParticipleHighLight(o);
    }

    @PostMapping(value = "/queryTermsField")
    @ResponseBody
    public Object queryTermsField(@RequestBody User o) {
        return service.queryTermsField(o);
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public Object update(@RequestBody User o) {
        return service.update(o);
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestBody User o) {
        return service.delete(o);
    }

    @PostMapping(value = "/deleteBatch")
    @ResponseBody
    public Object deleteBatch(@RequestBody User o) {
        return service.deleteBatch(o);
    }
}
