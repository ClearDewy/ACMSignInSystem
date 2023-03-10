package com.cleardewy.acmsis.service.Impl;

import com.cleardewy.acmsis.entity.JsonResult;
import com.cleardewy.acmsis.entity.User;
import com.cleardewy.acmsis.entity.UserStatus;
import com.cleardewy.acmsis.mapper.UserMapper;
import com.cleardewy.acmsis.mapper.UserStatusMapper;
import com.cleardewy.acmsis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cleardewy.acmsis.constant.ErorrMessage.*;

/**
 * @Author: cleardewy
 * @Date: 2022/10/13 19:26
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserStatusMapper userStatusMapper;

    @Override
    public JsonResult GetAllUser() {
        List<User> data;
        try {
            data = userMapper.GetAllUser();
        } catch (DataAccessException e) {
            log.error("{}--->{}", GetAllUserFail,e.getMessage());
            return new JsonResult<>(false, GetAllUserFail.toString(), e.getMessage());
        }
        return new JsonResult<>(true, null, data);
    }

    @Override
    public User GetUser(Integer Id) {
        return userMapper.GetUser(Id).get(0);
    }

    @Override
    public User GetId(String rule) throws DataAccessException {
        return userMapper.GetId(rule).get(0);
    }

    @Override
    public JsonResult AddUser(User user) {
        try {
            if (userMapper.AddUser(user) == 0) return new JsonResult(false, AddUserFail.toString());
        } catch (DataAccessException e) {
            log.error("{}--->{}", AddUserFail,e.getMessage());
            return new JsonResult(false, AddUserFail.toString(), e.getMessage());
        }
        UserStatus userStatus;
        try {
            userStatus = new UserStatus(GetId(user.getStuCardNum()).getId(), false);
            userStatusMapper.AddUserStatus(userStatus);
        } catch (DataAccessException e) {
            log.error("{}--->{}", AddUserStatusFail,e.getMessage());
            return new JsonResult(false, AddUserStatusFail.toString(), e.getMessage());
        }
        return new JsonResult(true, null);
    }

    @Override
    public JsonResult DeleteUser(User user) {
        try {
            userMapper.DeleteUser(user);
        } catch (DataAccessException e) {
            log.error("{}--->{}", DeleteUserFail,e.getMessage());
            return new JsonResult(false, DeleteUserFail.toString(), e.getMessage());
        }
        return new JsonResult(true, null);
    }

    @Override
    public JsonResult UpdateUser(User user) {
        try {
            userMapper.UpdateUser(user);
        } catch (DataAccessException e) {
            log.error("{}--->{}", UpdateUserFail,e.getMessage());
            return new JsonResult(false, UpdateUserFail.toString(), e.getMessage());
        }
        return new JsonResult(true, null);
    }
}
