package com.qingtuan.acmsis;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
@SpringBootApplication
@EnableAsync(proxyTargetClass=true) //开启异步注解
@MapperScan("com/qingtuan/acmsis/mapper")
public class LabelClockSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(LabelClockSystemApplication.class, args);
    }

}
