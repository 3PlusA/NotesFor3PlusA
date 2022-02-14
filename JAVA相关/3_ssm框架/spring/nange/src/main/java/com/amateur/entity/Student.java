package com.amateur.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Student {
    private Integer id;
    private String name;
    private Integer age;


    public Student(){
        System.out.println("这是无参构造函数");
    }
}


