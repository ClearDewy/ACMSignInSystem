package com.qingtuan.labelclocksystem.controller;

import com.qingtuan.labelclocksystem.entity.JsonResult;
import com.qingtuan.labelclocksystem.entity.TimeCount;
import com.qingtuan.labelclocksystem.entity.User;
import com.qingtuan.labelclocksystem.entity.UserStatus;
import com.qingtuan.labelclocksystem.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@ResponseBody
public class Api {
    @Autowired
    private Service service;

    @RequestMapping("/getalluser")
    JsonResult getalluser(){
        return service.GetAllUser();
    }

    @PostMapping("/adduer")
    JsonResult adduser(User user){
        System.out.println(user);
        JsonResult res = service.AddUser(user);
        System.out.println(res);
        return res;
    }

    @PostMapping("/deleteuser")
    JsonResult deleteuser(User user){
        return service.DeleteUser(user);
    }

    @PostMapping("/updateuser")
    JsonResult updateuser(User user){
        return service.UpdateUser(user);
    }

    @PostMapping("/clockin")
    JsonResult clockin(String rule){
        return service.ClockIn(rule);
    }

    @RequestMapping("/getalluserstatus")
    JsonResult getalluserstatus(){
        return service.GetAllUserStatus();
    }

    @PostMapping("/counttime")
    JsonResult counttime(TimeCount timeCount){
        return service.CountTime(timeCount);
    }

}
