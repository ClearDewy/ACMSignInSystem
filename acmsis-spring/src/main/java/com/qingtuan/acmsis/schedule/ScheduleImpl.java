package com.qingtuan.acmsis.schedule;

import com.qingtuan.acmsis.schedule.Schedule;
import com.qingtuan.acmsis.service.Impl.ServiceImpl;
import com.qingtuan.acmsis.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleImpl implements Schedule {
    @Autowired
    private UserStatusService userStatusService;

    /**
     * 每天2：00清空用户状态表
     */
    @Override
    @Scheduled(cron = "0 0 2 * * ?", zone = "GMT+8:00")
    public void DownAllUserStatus() {
        userStatusService.DownAllUserStatus();
    }
}
