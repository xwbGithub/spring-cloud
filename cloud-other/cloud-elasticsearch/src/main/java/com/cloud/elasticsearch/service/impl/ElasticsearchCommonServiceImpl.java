package com.cloud.elasticsearch.service.impl;

import com.cloud.elasticsearch.entity.CommonData;
import com.cloud.elasticsearch.service.ElasticsearchCommonService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Administrator
 * @description
 */
@Component
@Slf4j
public class ElasticsearchCommonServiceImpl implements ElasticsearchCommonService {


    @Resource
    private RestHighLevelClient esClient;

    @Override
    public Object insert(CommonData o) {
        return null;
    }

    @Override
    public Object batchInsert(CommonData o) {
        return null;
    }

    @Override
    public Object delete(CommonData o) {
        return null;
    }

    @Override
    public Object delete(String id) {
        return null;
    }

    @Override
    public Object delete(int id) {
        return null;
    }

    @Override
    public Object update(CommonData o) {
        return null;
    }

    @Override
    public Object deleteBatch(CommonData o) {
        return null;
    }

    @Override
    public Object query(CommonData o) {
        return null;
    }

    @Override
    public Object queryTerm(CommonData o) {
        return null;
    }

    @Override
    public Object queryTermOrder(CommonData o) {
        return null;
    }
}
