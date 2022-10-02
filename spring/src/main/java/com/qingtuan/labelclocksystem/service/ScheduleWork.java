package com.qingtuan.labelclocksystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleWork {
    @Autowired
    private Service service;

    @Scheduled(cron = "0 6 13 * * ?")
    public void DownAllUserStatus(){
        service.DownAllUserStatus();
    }
}
