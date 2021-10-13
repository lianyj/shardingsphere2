package com.example.shardingsphere2.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.shardingsphere2.entity.LabelAdmin;
import com.example.shardingsphere2.mapper.LabelAdminMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@DS("pub")
public class LabelAdminServiceImpl implements LabelAdminService {

    @Resource
    private LabelAdminMapper labelAdminMapper;

    @Override
    public LabelAdmin selectById(int id) {
        return labelAdminMapper.selectById(id);
    }

}
