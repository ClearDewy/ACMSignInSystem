package com.qingtuan.acmsis.service.Impl;

import com.qingtuan.acmsis.entity.Record;
import com.qingtuan.acmsis.entity.*;
import com.qingtuan.acmsis.mapper.RecordMapper;
import com.qingtuan.acmsis.mapper.UserMapper;
import com.qingtuan.acmsis.mapper.UserStatusMapper;
import com.qingtuan.acmsis.service.RecordService;
import com.qingtuan.acmsis.service.Service;
import com.qingtuan.acmsis.service.UserService;
import com.qingtuan.acmsis.service.UserStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

import static com.qingtuan.acmsis.constant.ErorrMessage.*;

@Slf4j
@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    @Autowired
    private UserStatusMapper userStatusMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserStatusService userStatusService;
    @Autowired
    private RecordService recordService;

    public JsonResult ClockIn(String rule) {
        UserStatus userStatus;
        User user;
        try {
            user = userService.GetId(rule);
        } catch (DataAccessException e) {
            log.warn("{}--->{}", UserMissed,e.getMessage());
            return new JsonResult(false, UserMissed.toString(), e.getMessage());
        }
        try {
            userStatus = userStatusService.GetUserStatus(user.getId());
        } catch (DataAccessException e) {
            return new JsonResult(false, GetUserStatusFail.toString(), e.getMessage());
        }

        try {
            if (userStatus.isIsAlive())
                recordService.AddRecord(new Record(userStatus.getUserId(), userStatus.getStartTime()));
        } catch (DataAccessException e) {
            log.error("{}--->{}", AddRecordFail,e.getMessage());
            return new JsonResult(false, AddRecordFail.toString(), e.getMessage());
        }
        userStatus.setIsAlive(!userStatus.isIsAlive());
        try {
            if (userStatusMapper.UpdateUserStatus(userStatus) == 0) return new JsonResult(false, UpdateUserStatusFail.toString());
        } catch (DataAccessException e) {
            log.error("{}--->{}", UpdateUserStatusFail,e.getMessage());
            return new JsonResult(false, UpdateUserStatusFail.toString(), e.getMessage());
        }
        if (userStatus.isIsAlive()) return new JsonResult(true, user.getName() + ClockInSuccess);
        else
            return new JsonResult(true, user.getName() + ClockOutSuccess + '\n' + "时长" + String.format("%.2f", 1.0 * (System.currentTimeMillis() - userStatus.getStartTime().getTime()) / (60 * 60 * 1000)) + "小时");
    }



}
