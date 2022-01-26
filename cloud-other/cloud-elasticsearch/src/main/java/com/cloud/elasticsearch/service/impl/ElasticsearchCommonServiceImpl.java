package com.cloud.elasticsearch.service.impl;

import cn.hutool.json.JSONUtil;
import com.cloud.elasticsearch.core.util.EsIndexNames;
import com.cloud.elasticsearch.entity.CommonData;
import com.cloud.elasticsearch.entity.DataDemo;
import com.cloud.elasticsearch.entity.User;
import com.cloud.elasticsearch.service.ElasticsearchCommonService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
    public Object insert(CommonData data) {
        //创建索引请求对象
        IndexRequest request = new IndexRequest();
        request.index(EsIndexNames.USER).id(data.getId());

        //es规定插入的数据为json
        String userJson = JSONUtil.parseObj(data).toString();
        request.source(userJson, XContentType.JSON);
        //发送请求获取相应
        try {
            IndexResponse index = esClient.index(request, RequestOptions.DEFAULT);
            //操作状态
            DocWriteResponse.Result result = index.getResult();
            log.info("插入信息{}", result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object batchInsert(CommonData o) {
        //创建索引请求对象

        long start = System.currentTimeMillis();
        BulkRequest bulkRequest = new BulkRequest();
        List<User> user = DataDemo.getUser(o.getStart(), o.getSize());
        user.forEach(u -> {
            bulkRequest.add(new IndexRequest(EsIndexNames.USER).id(u.getId()).source(JSONUtil.parseObj(u),
                    XContentType.JSON));
        });
        //发送请求获取相应
        try {
            BulkResponse bulk = esClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            //操作状态
            BulkItemResponse[] items = bulk.getItems();
            log.info("插入信息{}", Arrays.toString(items));
            long end = System.currentTimeMillis();
            log.info("创建{},用时{}", o.getStart() * o.getSize(), end - start);
            return Arrays.toString(items);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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
