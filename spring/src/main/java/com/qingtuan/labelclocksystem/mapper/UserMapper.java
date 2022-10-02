package com.qingtuan.labelclocksystem.mapper;
import com.qingtuan.labelclocksystem.entity.Record;
import com.qingtuan.labelclocksystem.entity.TimeCount;
import com.qingtuan.labelclocksystem.entity.User;
import com.qingtuan.labelclocksystem.entity.UserStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.dao.DataAccessException;
import java.util.List;

@Mapper
public interface UserMapper{

    List<User> GetAllUser() throws DataAccessException;

    List<User> GetUser(Integer Id) throws DataAccessException;

    List<User> GetId(String rule)throws DataAccessException;

    Integer AddUser(User user)throws DataAccessException;

    Integer DeleteUser(User user)throws DataAccessException;

    Integer UpdateUser(User user)throws DataAccessException;

    List<UserStatus> GetAllUserStatus()throws DataAccessException;
    List<UserStatus> GetUserStatus(Integer Id)throws DataAccessException;
    Integer AddUserStatus (UserStatus userStatus)throws DataAccessException;

    Integer UpdateUserStatus(UserStatus userStatus)throws DataAccessException;

    Integer DownAllUserStatus()throws DataAccessException;
    Integer AddRecord (Record record)throws DataAccessException;

    List<TimeCount> CountTime(TimeCount timeCount)throws DataAccessException;
}
