package com.cleardewy.acmsis.service;

import com.cleardewy.acmsis.entity.JsonResult;
import com.cleardewy.acmsis.entity.UserStatus;

/**
 * @Author: cleardewy
 * @Date: 2022/10/13 19:20
 */

public interface UserStatusService {
    JsonResult GetAllUserStatus();

    UserStatus GetUserStatus(Integer Id);

    Integer AddUserStatus(UserStatus userStatus);


    Integer DownAllUserStatus();
}
