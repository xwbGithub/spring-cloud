package com.cloud.elasticsearch.mapper.index;

/**
 * @description
 */

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 基础操作的es repository接口（定义的有通用的增删改查方法）
 *
 * @author gaozhy
 * @date 2018/12/29.9:26
 */
@Service
@Slf4j
public class UserRepository {

    @Resource
    private TransportClient client;

    public Object insert(Object object) {
        try {
            XContentBuilder contentBuilder = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("catid", "22")
                    .field("classify", 54)
                    .field("author", "ssve")
                    .field("id", "1")
                    .field("title", "菜鸟成长记")
                    .endObject();
            IndexResponse indexResponse = client.prepareIndex("index3", "user3", "10")
                    .setSource(contentBuilder)
                    .get();
            log.info("创建结果{}", indexResponse.toString());
            return indexResponse;
        } catch (Exception e) {
            log.error("创建异常{}", e.getMessage());
        }
        return null;
    }

    public Object delete(Object object) {
        return null;
    }

    public Object delete(String id) {
        return null;
    }

    public Object update(Object object) {
        return null;
    }

    public Object search(Object object) {
        return null;
    }

    public Object query(Object object) {
        return null;
    }
}
