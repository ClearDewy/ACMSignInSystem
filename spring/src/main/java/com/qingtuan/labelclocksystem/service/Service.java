package com.qingtuan.labelclocksystem.service;

import com.qingtuan.labelclocksystem.entity.*;
import com.qingtuan.labelclocksystem.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
@org.springframework.stereotype.Service
public class Service {
    private final static String GetAllUserFail="获取用户信息列表失败";
    private final static String GetUserFail="获取用户信息失败";
    private final static String AddUserFail="添加用户失败";
    private final static String AddUserStatusFail="添加用户状态失败";
    private final static String DeleteUserFail="删除用户失败";
    private final static String UpdateUserFail="更新用户信息失败";
    private final static String GetAllUserStatusFail="获取用户状态列表失败";
    private final static String GetUserStatusFail="获取用户状态失败";
    private final static String AddRecordFail="添加记录失败";
    private final static String ClockInSuccess="签到成功";
    private final static String ClockOutSuccess="退签成功";
    private final static String UpdateUserStatusFail="更新用户状态失败";
    private final static String TimeError="时间格式错误";
    private final static String CountTimeFail="时间统计失败";
    private final static String UserMissed="暂无此用户";

    @Autowired
    private UserMapper userMapper;

    public JsonResult GetAllUser() {
        List<User> data;
        try {
            data = userMapper.GetAllUser();
        } catch (DataAccessException e) {
            return new JsonResult<>(false, GetAllUserFail, e.getMessage());
        }
        return new JsonResult<>(true, null, data);
    }

    public User GetUser(Integer Id){
        return userMapper.GetUser(Id).get(0);
    }

    public User GetId(String rule)throws DataAccessException{
        return userMapper.GetId(rule).get(0);
    }

    public JsonResult AddUser(User user){
        try {
            if (userMapper.AddUser(user)==0)return new JsonResult(false,AddUserFail);
        }catch (DataAccessException e){
            return new JsonResult(false,AddUserFail,e.getMessage());
        }
        UserStatus userStatus;
        try {
            userStatus = new UserStatus(GetId(user.getStuCardNum()).getId(),false);
            userMapper.AddUserStatus(userStatus);
        }catch (DataAccessException e){
            return new JsonResult(false,AddUserStatusFail,e.getMessage());
        }
        return new JsonResult(true,null);
    }

    public JsonResult DeleteUser(User user){
        try {
            userMapper.DeleteUser(user);
        }catch (DataAccessException e){
            return new JsonResult(false,DeleteUserFail,e.getMessage());
        }
        return new JsonResult(true,null);
    }

    public JsonResult UpdateUser(User user){
        try {
            userMapper.UpdateUser(user);
        }catch (DataAccessException e){
            return new JsonResult(false,UpdateUserFail,e.getMessage());
        }
        return new JsonResult(true,null);
    }

    public JsonResult GetAllUserStatus(){
        List<UserStatus>userStatuses;
        try {
            userStatuses=userMapper.GetAllUserStatus();
            User user;
            for(UserStatus i:userStatuses){
                user=GetUser(i.getUserId());
                i.setName(user.getName());i.setStuNum(user.getStuNum());
            }
        }catch (DataAccessException e){
            return new JsonResult(false,GetAllUserStatusFail,e.getMessage());
        }
        return new JsonResult(true,null,userStatuses);
    }
    public UserStatus GetUserStatus(Integer Id){
        return userMapper.GetUserStatus(Id).get(0);
    }

    public Integer AddUserStatus(UserStatus userStatus){
        return userMapper.AddUserStatus(userStatus);
    }

    public Integer DownAllUserStatus(){
        return userMapper.DownAllUserStatus();
    }
    public JsonResult ClockIn(String rule){
        UserStatus userStatus;
        User user;
        try {
            user=GetId(rule);
        }catch (DataAccessException e){
            return new JsonResult(false,UserMissed,e.getMessage());
        }
        try {
            userStatus = GetUserStatus(user.getId());
        }catch (DataAccessException e){
            return new JsonResult(false,GetUserStatusFail,e.getMessage());
        }

        try {
            if (userStatus.isIsAlive())
                AddRecord(new Record(userStatus.getUserId(),userStatus.getStartTime()));
        }catch (DataAccessException e){
            return new JsonResult(false,AddRecordFail,e.getMessage());
        }
        userStatus.setIsAlive(!userStatus.isIsAlive());
        try {
            if(userMapper.UpdateUserStatus(userStatus)==0)return new JsonResult(false,UpdateUserStatusFail);
        }catch (DataAccessException e){
            return new JsonResult(false,UpdateUserStatusFail,e.getMessage());
        }
        if (userStatus.isIsAlive())return new JsonResult(true,user.getName()+ ClockInSuccess);
        else return new JsonResult(true,user.getName()+ ClockOutSuccess+'\n'+"时长"+String.format("%.2f", 1.0*(System.currentTimeMillis()-userStatus.getStartTime().getTime())/(60 * 60 * 1000))+"小时");
    }

    public Integer AddRecord(Record record){
        return userMapper.AddRecord(record);
    }

    public JsonResult CountTime(TimeCount timeCount){
        List<TimeCount> timeCounts;
        try {

            Date date = new Date(timeCount.getEndDate().getTime() + 24 * 60 * 60 * 1000);
            timeCount.setEndDate(date);
        }catch (DataAccessException e){
            return new JsonResult(false,TimeError,e.getMessage());
        }
        try {
            timeCounts=userMapper.CountTime(timeCount);
        }catch (DataAccessException e){
            return new JsonResult(false,CountTimeFail,e.getMessage());
        }
        return new JsonResult(true,null,timeCounts);
    }


}
