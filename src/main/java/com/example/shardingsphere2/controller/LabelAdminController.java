package com.example.shardingsphere2.controller;

import com.example.shardingsphere2.entity.LabelAdmin;
import com.example.shardingsphere2.service.LabelAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Slf4j
@RestController
@RequestMapping("/pub")
public class LabelAdminController {

    @Autowired
    private LabelAdminService labelAdminService;

    @GetMapping("/get/{id}")
    public Object sync(@PathVariable("id")Integer id) {
       LabelAdmin labelAdmin = labelAdminService.selectById(id);
      return labelAdmin;
    }
}
