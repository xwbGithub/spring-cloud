package com.cloud.elasticsearch.core.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @description 配置信息
 */
@Configuration
@Slf4j
public class EsConfig {

    @Value("${es.ip}")
    private String ip;
    @Value("${es.port}")
    private int port;
    @Value("${es.scheme}")
    private String scheme;

    /**
     * 初始化es客户端工具
     *
     * @Description: 初始化es客户端工具
     * @Date: 2022/1/12 17:12
     * @return: 返回创建的客户端工具
     **/
    @Bean
    public RestHighLevelClient initEsClient() {
        log.info("config 初始化elasticsearch开始.....");
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost(ip, port, scheme))
        );
        log.info("config 初始化elasticsearch完成.....");
        return restHighLevelClient;
    }
}
