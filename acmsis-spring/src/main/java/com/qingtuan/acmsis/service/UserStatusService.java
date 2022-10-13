package com.qingtuan.acmsis.service;

import com.qingtuan.acmsis.entity.JsonResult;
import com.qingtuan.acmsis.entity.UserStatus;
import org.springframework.stereotype.Service;

/**
 * @Author: Qingtuan
 * @Date: 2022/10/13 19:20
 */

public interface UserStatusService {
    JsonResult GetAllUserStatus();

    UserStatus GetUserStatus(Integer Id);

    Integer AddUserStatus(UserStatus userStatus);


    Integer DownAllUserStatus();
}
