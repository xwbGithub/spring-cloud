package com.cloud.elasticsearch.core.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @description 配置信息
 */
@Configuration
@Slf4j
public class EsConfig {

    @Bean
    public RestHighLevelClient initEsClient() {
        log.info("config 初始化elasticsearch开始.....");
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );
        log.info("config 初始化elasticsearch完成.....");
        return restHighLevelClient;
    }
}
