package com.example.shardingsphere2.datasource;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.provider.AbstractDataSourceProvider;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Map;

@Slf4j
@Configuration
@AutoConfigureBefore({DynamicDataSourceAutoConfiguration.class, SpringBootConfiguration.class})
public class DataSourceConfiguration {

    @Resource
    private DynamicDataSourceProperties properties;

    @Lazy
    @Resource(name = "shardingSphereDataSource")
    private DataSource shardingSphereDataSource;

    @Value("${spring.shardingsphere.rules.sharding.key-generators.snowflake.props.worker-id}")
    private Integer workerId;

    @Bean
    public DynamicDataSourceProvider dynamicDataSourceProvider() {
        log.info("Current workerId: {}", workerId);
        Map<String, DataSourceProperty> datasourceMap = properties.getDatasource();
        return new AbstractDataSourceProvider() {
            @Override
            public Map<String, DataSource> loadDataSources() {
                Map<String, DataSource> dataSourceMap = createDataSourceMap(datasourceMap);
                dataSourceMap.put("ds-sharding", shardingSphereDataSource);
                return dataSourceMap;
            }
        };
    }


    /**
     * ????????????????????????????????????
     * ???spring????????????????????????, ?????????????????????????????????
     * ???????????????????????????????????????????????????shardingjdbc????????????????????????
     */
    @Primary
    @Bean
    public DataSource dataSource(DynamicDataSourceProvider dynamicDataSourceProvider) {
        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
        dataSource.setPrimary(properties.getPrimary());
        dataSource.setStrict(properties.getStrict());
        dataSource.setStrategy(properties.getStrategy());
        dataSource.setProvider(dynamicDataSourceProvider);
        dataSource.setP6spy(properties.getP6spy());
        dataSource.setSeata(properties.getSeata());
        return dataSource;
    }
}
