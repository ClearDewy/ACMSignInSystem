//package com.cleardewy.labelclocksystem;
//
//import com.cleardewy.labelclocksystem.entity.TimeCount;
//import com.cleardewy.labelclocksystem.entity.User;
//import com.cleardewy.labelclocksystem.entity.UserStatus;
//import com.cleardewy.labelclocksystem.mapper.UserMapper;
//import com.cleardewy.labelclocksystem.service.Service;
//import org.apache.coyote.http11.filters.VoidInputFilter;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.sql.Date;
//import java.sql.Timestamp;
//import java.util.List;
//
//@SpringBootTest
//class LabelClockSystemApplicationTests {
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Autowired
//    private UserMapper userMapper;
//    private Service service;
//
//    @Test
//    void findAll(){
//        List<User> users = userMapper.GetAllUser();
//        System.out.println(users);
//        System.out.println("===================================");
//    }
//
//    @Test
//    void adduser(){
//        User user=new User("牟七","202121095555","5555555555","2021","计算机");
//        Integer res = userMapper.AddUser(user);
//        System.out.println(res);
//    }
//
//
//    @Test
//    void updateuser(){
//        User user=new User(12,"赵六","202121094455","4444455555","2022","计算机");
//        Integer res = userMapper.UpdateUser(user);
//        System.out.println(res);
//    }
//
//    @Test
//    void getid(){
//        String StuCardNum="1234567891";
//        List<User> IdList = userMapper.GetId(StuCardNum);
//        System.out.println(IdList);
//    }
//
//    @Test
//    void updateuserstatus(){
//        UserStatus userStatus=new UserStatus(20,true);
//        userMapper.UpdateUserStatus(userStatus);
//
//    }
//
//    @Test
//    void counttime(){
//        TimeCount timeCount=new TimeCount("2022-09-23","2022-09-24");
//        System.out.println("=============================");
//        Date date = new Date(timeCount.getEndDate().getTime() + 24 * 60 * 60 * 1000);
//        System.out.println(date);
//        timeCount.setEndDate(date);
//        System.out.println(timeCount);
//        List<TimeCount> timeCounts = userMapper.CountTime(timeCount);
//        System.out.println(timeCounts);
//    }
//
//    @Test
//    void Time(){
//        UserStatus userStatus=new UserStatus();
//        userStatus.setStartTime(new Timestamp((new java.util.Date().getTime()-1000)));
//
//        String t= String.valueOf((1.0*((new java.util.Date().getTime())-userStatus.getStartTime().getTime())/(60 * 60 * 1000)));
//        System.out.println(t);
//        System.out.println(userStatus.getStartTime().toLocalDateTime());
//    }
//
//}
