package com.cleardewy.acmsis.constant;

/**
 * @Author: cleardewy
 * @Date: 2022/10/13 19:35
 */
public enum ErorrMessage {
    GetAllUserFail("获取用户信息列表失败"),
    GetUserFail("获取用户信息失败"),
    AddUserFail("添加用户失败"),
    AddUserStatusFail("添加用户状态失败"),
    DeleteUserFail("删除用户失败"),
    UpdateUserFail("更新用户信息失败"),
    GetAllUserStatusFail("获取用户状态列表失败"),

    GetUserStatusFail("获取用户状态失败"),
    AddRecordFail("添加记录失败"),
    ClockInSuccess("签到成功"),
    ClockOutSuccess("退签成功"),
    UpdateUserStatusFail("更新用户状态失败"),
    TimeError("时间格式错误"),
    CountTimeFail("时间统计失败"),
    UserMissed("暂无此用户");






    private String message;

    ErorrMessage(String message){
        this.message=message;
    }


    /**
     * 覆盖
     * @return
     */
    @Override
    public String toString() {
        return message;
    }



}
