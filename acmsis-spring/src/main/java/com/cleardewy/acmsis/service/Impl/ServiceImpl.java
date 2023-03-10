package com.cleardewy.acmsis.service.Impl;

import com.cleardewy.acmsis.constant.ErorrMessage;
import com.cleardewy.acmsis.entity.JsonResult;
import com.cleardewy.acmsis.entity.User;
import com.cleardewy.acmsis.entity.UserStatus;
import com.cleardewy.acmsis.service.RecordService;
import com.cleardewy.acmsis.service.UserService;
import com.cleardewy.acmsis.entity.Record;
import com.cleardewy.acmsis.entity.*;
import com.cleardewy.acmsis.mapper.UserStatusMapper;
import com.cleardewy.acmsis.service.Service;
import com.cleardewy.acmsis.service.UserStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

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
            log.warn("{}--->{}", ErorrMessage.UserMissed,e.getMessage());
            return new JsonResult(false, ErorrMessage.UserMissed.toString(), e.getMessage());
        }
        try {
            userStatus = userStatusService.GetUserStatus(user.getId());
        } catch (DataAccessException e) {
            return new JsonResult(false, ErorrMessage.GetUserStatusFail.toString(), e.getMessage());
        }

        try {
            if (userStatus.isIsAlive())
                recordService.AddRecord(new Record(userStatus.getUserId(), userStatus.getStartTime()));
        } catch (DataAccessException e) {
            log.error("{}--->{}", ErorrMessage.AddRecordFail,e.getMessage());
            return new JsonResult(false, ErorrMessage.AddRecordFail.toString(), e.getMessage());
        }
        userStatus.setIsAlive(!userStatus.isIsAlive());
        try {
            if (userStatusMapper.UpdateUserStatus(userStatus) == 0) return new JsonResult(false, ErorrMessage.UpdateUserStatusFail.toString());
        } catch (DataAccessException e) {
            log.error("{}--->{}", ErorrMessage.UpdateUserStatusFail,e.getMessage());
            return new JsonResult(false, ErorrMessage.UpdateUserStatusFail.toString(), e.getMessage());
        }
        if (userStatus.isIsAlive()) return new JsonResult(true, user.getName() + ErorrMessage.ClockInSuccess);
        else
            return new JsonResult(true, user.getName() + ErorrMessage.ClockOutSuccess + '\n' + "时长" + String.format("%.2f", 1.0 * (System.currentTimeMillis() - userStatus.getStartTime().getTime()) / (60 * 60 * 1000)) + "小时");
    }



}
