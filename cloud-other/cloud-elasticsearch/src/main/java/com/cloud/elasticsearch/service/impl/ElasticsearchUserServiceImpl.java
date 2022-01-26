package com.cloud.elasticsearch.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cloud.elasticsearch.core.util.EsUtils;
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
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 * @description
 */
@SuppressWarnings("DuplicatedCode")
@Component
@Slf4j
public class ElasticsearchUserServiceImpl implements ElasticsearchUserService {


    @Resource
    private RestHighLevelClient esClient;

    @Override
    public Object insert(User o) {
        //创建索引请求对象
        IndexRequest request = new IndexRequest();
        String indexName = EsUtils.getIndexName(o.getIndexName());
        request.index(indexName).id(o.getId());

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

        long start = System.currentTimeMillis();
        BulkRequest bulkRequest = new BulkRequest();

        //List<User> user = DataDemo.getUser(o.getStart(), o.getSize());
        List<User> user = DataDemo.getUserField(o.getField(), o.getStart(), o.getSize());
        user.forEach(u -> {
            bulkRequest.add(new IndexRequest(EsUtils.getIndexName(o.getIndexName())).id(u.getId()).source(JSONUtil.parseObj(u),
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
    public Object batchInsertCity(User o) {
        //创建索引请求对象

        long start = System.currentTimeMillis();
        BulkRequest bulkRequest = new BulkRequest();

        List<User> user = DataDemo.getCityData(DataDemo.getCity());
        user.forEach(u -> {
            bulkRequest.add(new IndexRequest(EsUtils.getIndexName(o.getIndexName())).id(u.getId()).source(JSONUtil.parseObj(u),
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
    public Object delete(User o) {
        //创建索引请求对象
        DeleteRequest request = new DeleteRequest(EsUtils.getIndexName(o.getIndexName()));
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
        //发送请求获取相应
        try {
            DeleteRequest request = new DeleteRequest();
            request.id(id);
            DeleteResponse delete = esClient.delete(request, RequestOptions.DEFAULT);
            //操作状态
            log.info("删除信息{}", delete.status());
            return delete;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object delete(int id) {
        //发送请求获取相应
        try {
            DeleteRequest request = new DeleteRequest();
            request.id(id + "");
            DeleteResponse delete = esClient.delete(request, RequestOptions.DEFAULT);
            //操作状态
            log.info("删除信息{}", delete.status());
            return delete;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 批量删除
     *
     * @Description:
     * @Date: 2022/1/4 9:25
     * @return: null
     **/
    @Override
    public Object deleteBatch(User o) {
        //发送请求获取相应
        try {
            BulkRequest request = new BulkRequest();
            request.add(new DeleteRequest(EsUtils.getIndexName(o.getIndexName())).id("12"));
            request.add(new DeleteRequest(EsUtils.getIndexName(o.getIndexName())).id("13"));
            request.add(new DeleteRequest(EsUtils.getIndexName(o.getIndexName())).id("14"));
            request.add(new DeleteRequest(EsUtils.getIndexName(o.getIndexName())).id("16"));
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
            request.index(EsUtils.getIndexName(o.getIndexName())).id(o.getId());
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
        long start = System.currentTimeMillis();

        //查询索引中全部数据
        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());


        SearchRequest request = new SearchRequest();
        //对那个索引进行查询
        request.indices(EsUtils.getIndexName(o.getIndexName()));
        //查询对象
        request.source(query);
        try {
            SearchResponse search = esClient.search(request, RequestOptions.DEFAULT);

            //获取命中次数，查询结果有多少对象
            SearchHits hits = search.getHits();

            hits.forEach(h -> {
                log.info("\n查询的信息为{}", h.getSourceAsString());
            });

            long end = System.currentTimeMillis();
            log.info("用时：{}", end - start);
            return hits.getCollapseValues();
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
        query.trackScores(true);
        //开始位置
        query.from((o.getStart() - 1) * o.getSize());
        //每一页多少条
        query.size(o.getSize());

        SearchRequest request = new SearchRequest();
        //对那个索引进行查询
        request.indices(EsUtils.getIndexName(o.getIndexName()));
        //查询对象
        request.source(query);
        return EsUtils.search(esClient, request);
    }

    /**
     * 模糊查询
     *
     * @Description:
     * @Date: 2022/1/4 14:59
     * @return: null
     **/
    @Override
    public Object queryFuzzyQuery(User o) {

        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery(o.getField(), "*" + o.getFieldValue() + "*");

        SearchRequest searchRequest = EsUtils.packageSearchRequest(o.getStart(), o.getSize(), fuzzyQueryBuilder,
                EsUtils.getIndexName(o.getIndexName()));
        return EsUtils.search(esClient, searchRequest);
    }

    /**
     * 过滤字段
     *
     * @Description:
     * @Date: 2022/1/4 14:59
     * @return: null
     **/
    @Override
    public Object queryFieldIncludeExclude(User o) {

        //搜索名字中含有jack文档（name中只要包含jack即可）
        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        //必须包括的字段
        String[] includes = {"name"};
        //排除的字段
        String[] excludes = {"size", "age", "start"};
        query.fetchSource(includes, excludes);
        //开始位置
        query.from((o.getStart() - 1) * o.getSize());
        //每一页多少条
        query.size(o.getSize());

        SearchRequest request = new SearchRequest();
        //对那个索引进行查询
        request.indices(EsUtils.getIndexName(o.getIndexName()));
        //查询对象
        request.source(query);
        return EsUtils.search(esClient, request);
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


        query.trackScores(true);
        //排序字段
        query.sort("age", SortOrder.DESC);
        //开始位置
        query.from((o.getStart() - 1) * o.getSize());
        //每一页多少条
        query.size(o.getSize());

        SearchRequest request = new SearchRequest();
        //对那个索引进行查询
        request.indices(EsUtils.getIndexName(o.getIndexName()));
        //查询对象
        request.source(query);
        return EsUtils.search(esClient, request);
    }

    /**
     * 组合查询
     *
     * @Description:
     * @Date: 2022/1/13 16:52
     * @return: null
     **/
    @Override
    public Object queryTermCompose(User o) {

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //boolQueryBuilder.must(QueryBuilders.matchQuery("name","张三10"));
        //boolQueryBuilder.must(QueryBuilders.matchQuery("sex","10"));
        boolQueryBuilder.mustNot(QueryBuilders.matchQuery("sex", "男"));
//        boolQueryBuilder.should(QueryBuilders.matchQuery("name","张三"));

        SearchRequest searchRequest = EsUtils.packageSearchRequest(o.getStart(), o.getSize(), boolQueryBuilder,
                EsUtils.getIndexName(o.getIndexName()));

        return EsUtils.search(esClient, searchRequest);
    }

    /**
     * 范围查询
     *
     * @Description:
     * @Date: 2022/1/13 16:52
     * @return: null
     **/
    @Override
    public Object queryTermRange(User o) {

        //年龄的范围查询
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");

        //年龄大于等于100
        rangeQuery.gte(100);
        //年龄小于30000
        rangeQuery.lte(30000);

        SearchSourceBuilder query = new SearchSourceBuilder().query(rangeQuery);
        //开始位置
        query.from((o.getStart() - 1) * o.getSize());
        //每一页多少条
        query.size(o.getSize());

        SearchRequest request = new SearchRequest();
        //对那个索引进行查询
        request.indices(EsUtils.getIndexName(o.getIndexName()));
        //查询对象
        request.source(query);
        return EsUtils.search(esClient, request);
    }

    /**
     * 模糊查询
     *
     * @Description:
     * @Date: 2022/1/13 16:52
     * @return: null
     **/
    @Override
    public Object queryTermFuzzy(User o) {

        //模糊 ONE 只允许匹配到后面偏差一个字符 【james】 james1 james4
        FuzzyQueryBuilder fuzziness =
                QueryBuilders.fuzzyQuery(o.getField(), o.getFieldValue()).fuzziness(Fuzziness.ONE);
        // two允许匹配到的字符后面偏差两个字符 【james】 james12 james34
        FuzzyQueryBuilder fuzziness2 =
                QueryBuilders.fuzzyQuery(o.getField(), o.getFieldValue()).fuzziness(Fuzziness.TWO);

        SearchRequest searchRequest = EsUtils.packageSearchRequest(o.getStart(), o.getSize(), fuzziness,
                EsUtils.getIndexName(o.getIndexName()));

        return EsUtils.search(esClient, searchRequest);
    }

    @Override
    public Object queryTermHighLight(User o) {

        TermsQueryBuilder termQueryBuilder = QueryBuilders.termsQuery(o.getField(), o.getFieldValue());

        SearchSourceBuilder query = new SearchSourceBuilder().query(termQueryBuilder);

        query.highlighter(EsUtils.setHighlighter(o.getField()));

        SearchRequest request = EsUtils.initSearchRequest(EsUtils.getIndexName(o.getIndexName()), query);
        return EsUtils.search(esClient, request);
    }

    @Override
    public Object queryParticipleHighLight(User o) {

        MultiMatchQueryBuilder termQueryBuilder =
                QueryBuilders.multiMatchQuery(o.getFieldValue()+",keyword", o.getField()).analyzer("ik_max_word").field(
                o.getField());
        //TermsQueryBuilder termQueryBuilder = QueryBuilders.termsQuery(, );

        SearchSourceBuilder query = new SearchSourceBuilder().query(termQueryBuilder);

        query.highlighter(EsUtils.setHighlighter(o.getField()));

        SearchRequest request = EsUtils.initSearchRequest(EsUtils.getIndexName(o.getIndexName()), query);
        return EsUtils.searchHighlight(esClient, request);
    }

    @Override
    public Object queryAggregationBuilder(User o) {

        SearchSourceBuilder query = new SearchSourceBuilder();

        //年龄最大的
        AggregationBuilder maxAge = AggregationBuilders.max("maxAge").field(o.getField());
        query.aggregation(maxAge);

        SearchRequest request = new SearchRequest();
        //对那个索引进行查询
        request.indices(EsUtils.getIndexName(o.getIndexName()));
        //查询对象
        request.source(query);
        return EsUtils.search(esClient, request);
    }

    @Override
    public Object queryTermsField(User o) {

        //分组查询
        TermsAggregationBuilder ageGroup = AggregationBuilders.terms("ageGroup").field(o.getField());
        SearchSourceBuilder query = new SearchSourceBuilder();
        query.aggregation(ageGroup);

        SearchRequest request = new SearchRequest();
        //对那个索引进行查询
        request.indices(EsUtils.getIndexName(o.getIndexName()));
        //查询对象
        request.source(query);
        return EsUtils.search(esClient, request);
    }
}
