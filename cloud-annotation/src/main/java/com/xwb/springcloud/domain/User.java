package com.xwb.springcloud.domain;

import com.xwb.springcloud.annotation.json.JsonFormat;
import com.xwb.springcloud.annotation.json.JsonIgnore;
import com.xwb.springcloud.annotation.json.JsonProperty;

import java.util.Date;

public class User {
    @JsonIgnore
    private Integer id;
    @JsonProperty("user_name")
    private String name;
    @JsonProperty("user_address")
    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birth;//工资


    public User() {
    }

    public User(Integer id, String name, String address, Date birth) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birth = birth;
    }

    public User(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", birth=" + birth +
                '}';
    }
}
