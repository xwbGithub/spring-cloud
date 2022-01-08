package com.cloud.elasticsearch.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cloud.elasticsearch.core.util.EsIndexNames;
import com.cloud.elasticsearch.entity.DataDemo;
import com.cloud.elasticsearch.entity.User;
import com.cloud.elasticsearch.service.ElasticsearchUserService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author Administrator
 * @description
 */
@Component
@Slf4j
public class ElasticsearchUserServiceImpl implements ElasticsearchUserService {


    @Resource
    private RestHighLevelClient esClient;

    @Override
    public Object insert(User o) {
        //创建索引请求对象
        IndexRequest request = new IndexRequest();
        request.index(EsIndexNames.USER).id(o.getId());

        //es规定插入的数据为json
        String userJson = JSONUtil.parseObj(o).toString();
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
    public Object batchInsert(User o) {
        //创建索引请求对象

        BulkRequest bulkRequest = new BulkRequest();
        List<User> user = DataDemo.getUser(o.getStart(), o.getSize());
        user.forEach(u -> {
            log.info("user,{}", u);
            bulkRequest.add(new IndexRequest(EsIndexNames.USER).id(u.getId()).source(JSONUtil.parseObj(u),
                    XContentType.JSON));
        });
        //发送请求获取相应
        try {
            BulkResponse bulk = esClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            //操作状态
            BulkItemResponse[] items = bulk.getItems();
            log.info("插入信息{}", items.toString());
            return items.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public Object delete(User o) {
        //创建索引请求对象
        DeleteRequest request = new DeleteRequest(EsIndexNames.USER);
        request.id(o.getId());
        //发送请求获取相应
        try {
            DeleteResponse delete = esClient.delete(request, RequestOptions.DEFAULT);
            //操作状态
            log.info("删除信息{}", delete.getResult());
            return delete.getResult();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object delete(String id) {
//        //创建索引请求对象
//        DeleteIndexRequest request = new DeleteIndexRequest(id);
//        //发送请求获取相应
//        try {
//            AcknowledgedResponse delete = esClient.indices().delete(request, RequestOptions.DEFAULT);
//            //操作状态
//            log.info("删除信息{}", delete);
//            return null;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
        return null;
    }

    @Override
    public Object delete(int id) {
        return null;
    }

    /**
     * 批量删除
     *
     * @Description:
     * @Date: 2022/1/4 9:25
     * @return: null
     **/
    @Override
    public Object deleteBatch(User user) {
        //发送请求获取相应
        try {
            BulkRequest request = new BulkRequest();
            request.add(new DeleteRequest(EsIndexNames.USER).id("12"));
            request.add(new DeleteRequest(EsIndexNames.USER).id("13"));
            request.add(new DeleteRequest(EsIndexNames.USER).id("14"));
            request.add(new DeleteRequest(EsIndexNames.USER).id("16"));
            BulkResponse bulk = esClient.bulk(request, RequestOptions.DEFAULT);
            //操作状态
            log.info("删除信息{}", bulk.status());
            return bulk.status();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object update(User o) {

        //发送请求获取相应
        try {
            UpdateRequest request = new UpdateRequest();
            request.index(EsIndexNames.USER).id(o.getId());
            JSONObject jsonObject = JSONUtil.parseObj(o);
            request.doc(jsonObject);
            UpdateResponse update = esClient.update(request, RequestOptions.DEFAULT);
            //操作状态
            log.info("删除信息{}", update.getResult().toString());
            return update.getResult().toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 全量查询
     *
     * @param o
     * @return
     */
    @Override
    public Object query(User o) {

        //查询索引中全部数据
        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());


        SearchRequest request = new SearchRequest();
        //对那个索引进行查询
        request.indices(EsIndexNames.USER);
        //查询对象
        request.source(query);
        try {
            SearchResponse search = esClient.search(request, RequestOptions.DEFAULT);

            //获取命中次数，查询结果有多少对象
            SearchHits hits = search.getHits();

            hits.forEach(h -> {
                log.info("\n查询的信息为{}", h.getSourceAsString());
            });
            return hits;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 分页查询
     *
     * @Description:
     * @Date: 2022/1/4 14:59
     * @return: null
     **/
    @Override
    public Object queryTerm(User o) {

        //查询name=输入的值的数据
        //SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.termQuery("name", o.getName()));

        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        //开始位置
        query.from((o.getStart() - 1) * o.getSize());
        //每一页多少条
        query.size(o.getSize());

        SearchRequest request = new SearchRequest();
        //对那个索引进行查询
        request.indices(EsIndexNames.USER);
        //查询对象
        request.source(query);
        try {
            SearchResponse search = esClient.search(request, RequestOptions.DEFAULT);
            SearchHits hits = search.getHits();

            StringBuffer sb = new StringBuffer();
            hits.forEach(h -> {
                sb.append("\n").append(h.getSourceAsString());
            });
            log.info("查询到的数据{}", sb);
            return hits;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 排序
     *
     * @Description:
     * @Date: 2022/1/4 14:59
     * @return: null
     **/
    @Override
    public Object queryTermOrder(User o) {


        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        //排序字段
        query.sort("age", SortOrder.DESC);

        SearchRequest request = new SearchRequest();
        //对那个索引进行查询
        request.indices(EsIndexNames.USER);
        //查询对象
        request.source(query);
        try {
            SearchResponse search = esClient.search(request, RequestOptions.DEFAULT);
            SearchHits hits = search.getHits();

            StringBuffer sb = new StringBuffer();
            hits.forEach(h -> {
                sb.append("\n").append(h.getSourceAsString());
            });
            log.info("查询到的数据{}", sb);
            return hits;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
