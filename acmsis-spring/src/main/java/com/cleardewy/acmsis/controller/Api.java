package com.cleardewy.acmsis.controller;

import com.cleardewy.acmsis.service.RecordService;
import com.cleardewy.acmsis.service.UserService;
import com.cleardewy.acmsis.service.UserStatusService;
import com.cleardewy.acmsis.entity.JsonResult;
import com.cleardewy.acmsis.entity.TimeCount;
import com.cleardewy.acmsis.entity.User;
import com.cleardewy.acmsis.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
@Slf4j
public class Api {
    @Autowired
    private Service service;
    @Autowired
    private UserService userService;
    @Autowired
    private RecordService recordService;
    @Autowired
    private UserStatusService userStatusService;

    @RequestMapping("/getalluser")
    public JsonResult getalluser() {
        return userService.GetAllUser();
    }

    @PostMapping("/adduer")
    public JsonResult adduser(User user) {
        System.out.println(user);
        JsonResult res = userService.AddUser(user);
        System.out.println(res);
        return res;
    }

    @DeleteMapping("/deleteuser")
    public JsonResult deleteuser(User user) {
        return userService.DeleteUser(user);
    }

    @PutMapping("/updateuser")
    public JsonResult updateuser(User user) {
        return userService.UpdateUser(user);
    }

    @PostMapping("/clockin")
    public JsonResult clockin(String rule) {
        return service.ClockIn(rule);
    }

    @RequestMapping("/getalluserstatus")
    public JsonResult getalluserstatus() {
        return userStatusService.GetAllUserStatus();
    }

    @PostMapping("/counttime")
    public JsonResult counttime(TimeCount timeCount) {
        return recordService.CountTime(timeCount);
    }

}
