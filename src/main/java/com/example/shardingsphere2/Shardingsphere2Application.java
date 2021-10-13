package com.example.shardingsphere2;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@Slf4j
@SpringBootApplication
@MapperScan("com.example.shardingsphere2.mapper")
@PropertySource("classpath:shardingsphere.properties")
public class Shardingsphere2Application {

	public static void main(String[] args) {
		SpringApplication. run(Shardingsphere2Application.class, args);
	}

}
