package com.gtt.service;

import com.gtt.entity.Role;

import java.util.List;

public interface RoleService {

    /*
    * 根据用户ID查角色集合
    * */
    public List<Role> findByUserId(Integer id);

    /*
     * 根据用户ID查实体
     * */
    public Role findById(Integer id);
}
