package com.cleardewy.acmsis.mapper;

import com.cleardewy.acmsis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> GetAllUser() throws DataAccessException;

    List<User> GetUser(Integer Id) throws DataAccessException;

    List<User> GetId(String rule) throws DataAccessException;

    Integer AddUser(User user) throws DataAccessException;

    Integer DeleteUser(User user) throws DataAccessException;

    Integer UpdateUser(User user) throws DataAccessException;


}
