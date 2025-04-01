package com.subvert.server.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author xujianguo
 * @date 2025/4/1
 * @description
 */

@Configuration
@EnableTransactionManagement
public class TransactionManagerConfig {

    private final DataSource dataSource;

    public TransactionManagerConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
