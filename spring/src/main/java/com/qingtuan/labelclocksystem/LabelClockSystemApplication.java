package com.qingtuan.labelclocksystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com/qingtuan/labelclocksystem/mapper")
public class LabelClockSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabelClockSystemApplication.class, args);
    }

}
