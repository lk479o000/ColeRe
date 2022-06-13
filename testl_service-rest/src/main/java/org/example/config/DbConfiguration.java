package org.example.config;

import com.weibi.core.commons.db.util.DataSourceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Db配置
 * @author huopengbo
 * @version 1.0
 * @date 2020-05-07 10:39
 */
@Slf4j
@Configuration
public class DbConfiguration {

    @Resource
    private DataSourceFactory dataSourceFactory;

    @Bean(name = "bizMasterTemplate")
    public JdbcTemplate getBizMasterTemplate(){
        return dataSourceFactory.generateTemplate("bizMaster");
    }

    @Bean(name = "bizSlaveTemplate")
    public JdbcTemplate getBizSlaveTemplate(){
        return dataSourceFactory.generateTemplate("bizSlave1");
    }

    @Bean(name = "bizSlave2Template")
    public JdbcTemplate getBizSlave2Template(){
        return dataSourceFactory.generateTemplate("bizSlave2");
    }

}
