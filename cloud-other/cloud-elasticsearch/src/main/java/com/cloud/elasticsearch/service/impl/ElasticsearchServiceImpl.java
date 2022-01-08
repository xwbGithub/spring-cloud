package com.cloud.elasticsearch.service.impl;

import com.cloud.elasticsearch.core.util.EsUtils;
import com.cloud.elasticsearch.core.util.EsIndexNames;
import com.cloud.elasticsearch.service.ElasticsearchService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.cluster.metadata.MappingMetadata;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @author Administrator
 * @description
 */
@Component
@Slf4j
public class ElasticsearchServiceImpl implements ElasticsearchService {

    @Resource
    RestHighLevelClient esClient;

    @Override
    public Object insert(Object o) {

        //创建索引请求对象
        CreateIndexRequest request = new CreateIndexRequest(EsIndexNames.USER);
        //发送请求获取相应
        try {
            CreateIndexResponse createIndexResponse = esClient.indices().create(request, RequestOptions.DEFAULT);
            //操作状态
            boolean acknowledged = createIndexResponse.isAcknowledged();
            log.info("插入信息{}", acknowledged);
            return acknowledged;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            EsUtils.closeEs(esClient);
        }
    }

    @Override
    public Object delete(Object o) {
        //创建索引请求对象
        DeleteIndexRequest request = new DeleteIndexRequest(EsIndexNames.USER);
        //发送请求获取相应
        try {
            AcknowledgedResponse delete = esClient.indices().delete(request, RequestOptions.DEFAULT);
            //操作状态
            log.info("删除信息{}", delete);
            return delete;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            EsUtils.closeEs(esClient);
        }
    }

    @Override
    public Object delete(String id) {
        //创建索引请求对象
        DeleteIndexRequest request = new DeleteIndexRequest(id);
        //发送请求获取相应
        try {
            AcknowledgedResponse delete = esClient.indices().delete(request, RequestOptions.DEFAULT);
            //操作状态
            log.info("删除信息{}", delete);
            return delete;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            EsUtils.closeEs(esClient);
        }
    }

    @Override
    public Object delete(int id) {
        return null;
    }

    @Override
    public Object update(Object o) {
        return null;
    }

    @Override
    public Object query(Object o) {
        RestHighLevelClient esClient = EsUtils.createEsClient();
        GetIndexRequest request = new GetIndexRequest(EsIndexNames.USER);
        try {
            GetIndexResponse response = esClient.indices().get(request, RequestOptions.DEFAULT);
            Map<String, MappingMetadata> mappings = response.getMappings();
            log.info("查询信息为：{}", mappings);
            return mappings;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            EsUtils.closeEs(esClient);
        }
    }
}
