package com.qingtuan.acmsis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer Id;
    private String Name;
    private String StuNum;
    private String StuCardNum;
    private String Grade;
    private String Major;

    public User(String name, String stuNum, String stuCardNum, String grade, String major) {
        Name = name;
        StuNum = stuNum;
        StuCardNum = stuCardNum;
        Grade = grade;
        Major = major;
    }

}
