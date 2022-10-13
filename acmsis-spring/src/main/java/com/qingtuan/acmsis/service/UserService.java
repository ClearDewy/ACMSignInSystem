package com.qingtuan.acmsis.service;

import com.qingtuan.acmsis.entity.JsonResult;
import com.qingtuan.acmsis.entity.User;
import org.springframework.stereotype.Service;

/**
 * @Author: Qingtuan
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
