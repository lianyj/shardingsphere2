package com.example.shardingsphere2.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.shardingsphere2.entity.LabelAdmin;
import org.apache.ibatis.annotations.Param;

@DS("pub")
public interface LabelAdminMapper {

    LabelAdmin selectById(@Param("id") Integer id);
}