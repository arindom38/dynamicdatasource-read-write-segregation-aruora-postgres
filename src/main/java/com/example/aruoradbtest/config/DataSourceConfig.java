package com.example.aruoradbtest.config;

import com.example.aruoradbtest.config.datasource.DynamicDataSource;
import com.example.aruoradbtest.enums.DataSourceType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.writer")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.reader")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(DataSource masterDataSource, DataSource slaveDataSource){
        Map<Object,Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.WRITER.name(),masterDataSource);
        targetDataSources.put(DataSourceType.READER.name(),slaveDataSource);
        return new DynamicDataSource(masterDataSource,targetDataSources);
    }



}
