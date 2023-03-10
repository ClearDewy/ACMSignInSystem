package com.cleardewy.acmsis.mapper;

import com.cleardewy.acmsis.entity.UserStatus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @Author: cleardewy
 * @Date: 2022/10/13 15:26
 */
@Mapper
public interface UserStatusMapper {
    List<UserStatus> GetAllUserStatus() throws DataAccessException;

    List<UserStatus> GetUserStatus(Integer Id) throws DataAccessException;

    Integer AddUserStatus(UserStatus userStatus) throws DataAccessException;

    Integer UpdateUserStatus(UserStatus userStatus) throws DataAccessException;

    Integer DownAllUserStatus() throws DataAccessException;
}
