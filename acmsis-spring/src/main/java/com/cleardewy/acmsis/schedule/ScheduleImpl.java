package com.cleardewy.acmsis.schedule;

import com.cleardewy.acmsis.service.UserStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@Slf4j
public class ScheduleImpl implements Schedule {
    @Autowired
    private UserStatusService userStatusService;

    /**
     * 每天2：00清空用户状态表
     */
    @Override
    @Scheduled(cron = "0 0 2 * * ?", zone = "GMT+8:00")
    public void DownAllUserStatus() {
        log.info("<--------------------清空未签退用户-------------------->");
        userStatusService.DownAllUserStatus();
        log.info("<--------------------清空完毕-------------------->");
    }
}
