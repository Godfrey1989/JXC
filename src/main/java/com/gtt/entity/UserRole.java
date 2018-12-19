package com.gtt.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_userRole")
public class UserRole {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getId() {

        return id;
    }

    public User getUser() {
        return user;
    }

    public Role getRole() {
        return role;
    }
}
