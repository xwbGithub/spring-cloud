package org.xwb.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.xwb.springcloud.dao.*")
public class MyBatisConfig {
}
