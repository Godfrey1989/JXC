package com.gtt.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/*
* 角色实体
* */

@Entity
@Table(name="t_user")
public class User {

    @Id
    @GeneratedValue
    private Integer id; //编号
    @NotEmpty(message = "请输入用户名")
    @Column(length = 50)
    private String userName;
    @NotEmpty(message = "请输入密码")
    @Column(length = 50)
    private String password;
    @Column(length = 50)
    private String trueName;
    @Column(length = 100)
    private String remarks;
    @Transient
    private String roles;

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getRoles() {

        return roles;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getId() {

        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getTrueName() {
        return trueName;
    }

    public String getRemarks() {
        return remarks;
    }
}
