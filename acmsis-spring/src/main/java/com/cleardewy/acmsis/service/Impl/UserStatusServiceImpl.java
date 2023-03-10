package com.cleardewy.acmsis.service.Impl;

import com.cleardewy.acmsis.constant.ErorrMessage;
import com.cleardewy.acmsis.entity.JsonResult;
import com.cleardewy.acmsis.entity.UserStatus;
import com.cleardewy.acmsis.mapper.UserStatusMapper;
import com.cleardewy.acmsis.service.UserStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: cleardewy
 * @Date: 2022/10/13 19:28
 */
@Slf4j
@Service
public class UserStatusServiceImpl implements UserStatusService {
    @Autowired
    private UserStatusMapper userStatusMapper;

    @Override
    public JsonResult GetAllUserStatus() {
        List<UserStatus> userStatuses;
        try {
            userStatuses = userStatusMapper.GetAllUserStatus();
        } catch (DataAccessException e) {
            log.error("{}--->{}", ErorrMessage.GetAllUserStatusFail,e.getMessage());
            return new JsonResult(false, ErorrMessage.GetAllUserStatusFail.toString(), e.getMessage());
        }
        return new JsonResult(true, null, userStatuses);
    }

    @Override
    public UserStatus GetUserStatus(Integer Id) {
        return userStatusMapper.GetUserStatus(Id).get(0);
    }

    @Override
    public Integer AddUserStatus(UserStatus userStatus) {
        return userStatusMapper.AddUserStatus(userStatus);
    }

    @Override
    public Integer DownAllUserStatus() {
        return userStatusMapper.DownAllUserStatus();
    }
}
