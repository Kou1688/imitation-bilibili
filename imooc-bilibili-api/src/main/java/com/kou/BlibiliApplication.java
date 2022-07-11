package com.kou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author KouChaoJie
 * @since: 2022/7/6 15:30
 */
@MapperScan(value = "com.kou.bilibili.dao")
@SpringBootApplication
public class BlibiliApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(BlibiliApplication.class, args);
    }
}
