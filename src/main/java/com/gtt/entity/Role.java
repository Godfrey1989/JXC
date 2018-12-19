package com.gtt.entity;

import javax.persistence.*;
/*
*
* 权限实体*/

@Entity
@Table(name="t_role")
public class Role {

    @Id
    @GeneratedValue
    private Integer id; //编号
    @Column(length = 50)
    private String name;
    @Column(length = 100)
    private String remarks;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getId() {

        return id;
    }

    public String getRemarks() {
        return remarks;
    }
}
