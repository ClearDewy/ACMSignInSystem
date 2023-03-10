package com.cleardewy.acmsis.service;

import com.cleardewy.acmsis.entity.JsonResult;
import com.cleardewy.acmsis.entity.User;

/**
 * @Author: cleardewy
 * @Date: 2022/10/13 19:19
 */

public interface UserService {
    public JsonResult GetAllUser();

    User GetUser(Integer Id);

    User GetId(String rule);

    JsonResult AddUser(User user);

    JsonResult DeleteUser(User user);


    JsonResult UpdateUser(User user);
}
