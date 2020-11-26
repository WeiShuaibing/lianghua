package com.bishe.lianghua;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bishe.lianghua.dao")
public class LianghuaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LianghuaApplication.class, args);
    }

}
