package com.jerry.validation.model;

import com.jerry.validation.constraints.ContainsString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class User {
    @NotBlank
    private String name;

    @Max(value = 120, message = "年龄最大不能超过120")
    @Min(10)
    private Integer age;

    @ContainsString(str="test", message = "必须包含指定内容{str}")
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
