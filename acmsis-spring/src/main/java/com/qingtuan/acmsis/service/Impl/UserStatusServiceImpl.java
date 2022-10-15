package com.qingtuan.acmsis.service.Impl;

import com.qingtuan.acmsis.entity.JsonResult;
import com.qingtuan.acmsis.entity.User;
import com.qingtuan.acmsis.entity.UserStatus;
import com.qingtuan.acmsis.mapper.UserStatusMapper;
import com.qingtuan.acmsis.service.UserService;
import com.qingtuan.acmsis.service.UserStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.qingtuan.acmsis.constant.ErorrMessage.GetAllUserStatusFail;
import static com.qingtuan.acmsis.constant.ErorrMessage.UpdateUserFail;

/**
 * @Author: Qingtuan
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
            log.error("{}--->{}", GetAllUserStatusFail,e.getMessage());
            return new JsonResult(false, GetAllUserStatusFail.toString(), e.getMessage());
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
