package com.gtt.entity;

import javax.persistence.*;
/*
*
* 菜单实体*/

@Entity
@Table(name="t_menu")
public class Menu {

    @Id
    @GeneratedValue
    private Integer id; //编号
    @Column(length = 50)
    private String name;

    @Column(length = 200)
    private String url;

    private Integer state;

    @Column(length = 100)
    private String icon;

    private Integer pId;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Integer getState() {
        return state;
    }

    public String getIcon() {
        return icon;
    }

    public Integer getpId() {
        return pId;
    }
}
