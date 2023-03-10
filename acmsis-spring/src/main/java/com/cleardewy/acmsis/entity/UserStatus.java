package com.cleardewy.acmsis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStatus {
    private Integer UserId;
    private String Name;
    private String StuNum;
    private boolean IsAlive;
    private Timestamp StartTime;

    public UserStatus(Integer id, boolean isAlive) {
        UserId = id;
        IsAlive = isAlive;
    }
}
