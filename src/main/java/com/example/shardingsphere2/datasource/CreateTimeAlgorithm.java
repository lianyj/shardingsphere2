package com.example.shardingsphere2.datasource;


import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;

/**
 * @description: 自定义创建时间算法（年月）
 **/
public class CreateTimeAlgorithm implements StandardShardingAlgorithm<String> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        String createTime = shardingValue.getValue();
//        Date date = shardingValue.getValue();
//        String yearMonth = new SimpleDateFormat("yyyyMM").format(date);
        String yearMonth = createTime.substring(0,7).replaceAll("-","");
        for (String availableTableName : availableTargetNames) {
            if (availableTableName.endsWith(yearMonth)) {
                return availableTableName;
            }
        }
        return null;
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<String> rangeShardingValue) {
        return null;
    }

    @Override
    public void init() {

    }

    @Override
    public String getType() {
        return "CLASS_BASED_DATE";
    }

    @Override
    public Properties getProps() {
        return null;
    }

    @Override
    public void setProps(Properties properties) {

    }
}
