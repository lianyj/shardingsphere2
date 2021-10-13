package com.example.shardingsphere2.datasource;

import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Properties;

/**
 * @description: 强制路由算法
 **/
public class HintYearMonthAlgorithm implements HintShardingAlgorithm<String> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<String> hintShardingValue) {
        Collection<String> result = new LinkedList<>();
        for (String availableTargetName : availableTargetNames) {
            for (String value : hintShardingValue.getValues()) {
                if (availableTargetName.endsWith(value)){
                    result.add(availableTargetName);
                }
            }
        }
        return result;
    }

    @Override
    public void init() {

    }

    @Override
    public String getType() {
        return "CLASS_BASED_HINT";
    }

    @Override
    public Properties getProps() {
        return null;
    }

    @Override
    public void setProps(Properties properties) {

    }
}
