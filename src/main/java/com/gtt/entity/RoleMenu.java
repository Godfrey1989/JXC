package com.gtt.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_roleMenu")
public class RoleMenu {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "menuId")
    private Menu menu;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Integer getId() {

        return id;
    }

    public Role getRole() {
        return role;
    }

    public Menu getMenu() {
        return menu;
    }
}
