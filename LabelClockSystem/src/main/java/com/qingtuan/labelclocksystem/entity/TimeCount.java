package com.qingtuan.labelclocksystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeCount{
    private Integer UserId;
    private String Name;
    private String StuNum;
    private Date StartDate;
    private Date EndDate;
    private Double Time;

    @SneakyThrows
    public TimeCount(String startDate, String endDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        StartDate= new Date(simpleDateFormat.parse(startDate).getTime());
        EndDate=new Date(simpleDateFormat.parse(endDate).getTime());
    }
    @SneakyThrows
    public TimeCount(Integer id, String startDate, String endDate){
        UserId=id;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        StartDate= new Date(simpleDateFormat.parse(startDate).getTime());
        EndDate=new Date(simpleDateFormat.parse(endDate).getTime());
    }
    public TimeCount(Date startDate,Date endDate){
        StartDate=startDate;
        EndDate=endDate;
    }
    public TimeCount(Integer id,Date startDate,Date endDate){
        UserId=id;
        StartDate=startDate;
        EndDate=endDate;
    }
}
