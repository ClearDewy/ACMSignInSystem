package com.cleardewy.acmsis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
@SpringBootApplication
@EnableAsync(proxyTargetClass=true) //开启异步注解
@MapperScan("com/cleardewy/acmsis/mapper")
public class LabelClockSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(LabelClockSystemApplication.class, args);
    }

}
