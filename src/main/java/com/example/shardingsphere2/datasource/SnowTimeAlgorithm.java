package com.example.shardingsphere2.datasource;

import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collection;
import java.util.Properties;

/**
 * 雪花算法
 * @description: 自定义按年月分表算法
 * Snowflake算法生成的ID结构：
 * 最高位是符号位，始终为0，不可用。
 * 41位的时间序列，精确到毫秒级，41位的长度可以使用69年。
 * 5位的数据中心标识，5位的长度最多支持部署32个节点。
 * 8位的机器标识，8位的长度最多支持部署255个节点。
 * 12位的计数序列号，序列号即一系列的自增id，可以支持同一节点同一毫秒生成多个ID序号，12位的计数序列号支持每个节点每毫秒产生4095个ID序号。
 * https://shardingsphere.apache.org/document/current/cn/features/sharding/concept/key-generator/
 **/
public class SnowTimeAlgorithm implements StandardShardingAlgorithm<Long> {

    private static final long timestampLeftShift;
    private static final DateTimeFormatter dtForm;
    public static final long EPOCH;

    static {
        timestampLeftShift = 22L;
        dtForm = DateTimeFormatter.ofPattern("yyyyMM");

        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.NOVEMBER, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        EPOCH = calendar.getTimeInMillis();
    }

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        // 反解析Snowflake生成的ID结构
        long snowflakeKey = shardingValue.getValue();
        String snowflakeBit = Long.toBinaryString(snowflakeKey);
        int bitLen = snowflakeBit.length();
        int timeStart = (int) (bitLen < timestampLeftShift ? 0 : bitLen - timestampLeftShift);
        String timestampStr = timeStart == 0 ? "0" : snowflakeBit.substring(0, timeStart);
        // 解析得到时间戳
        long timestamp = Long.parseLong(timestampStr, 2) + EPOCH;
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        String tableExt = localDateTime.format(dtForm);
        for (String availableTableName : availableTargetNames) {
            if (availableTableName.endsWith(tableExt)) {
                return availableTableName;
            }
        }
        return null;
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
        return null;
    }

    @Override
    public void init() {

    }

    @Override
    public String getType() {
        return "CLASS_BASED_SNOW";
    }

    @Override
    public Properties getProps() {
        return null;
    }

    @Override
    public void setProps(Properties properties) {

    }
}
