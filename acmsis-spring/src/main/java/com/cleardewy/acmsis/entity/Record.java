package com.cleardewy.acmsis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    private Integer UserId;
    private Timestamp StartTime;
    private Timestamp EndTime;
    private Double Time;

    public Record(Integer userId, Timestamp startTime) {
        UserId = userId;
        StartTime = startTime;
    }

}
