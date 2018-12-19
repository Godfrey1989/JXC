package com.gtt.service;

import com.gtt.entity.Menu;

import java.util.List;

public interface MenuService {

    public List<Menu> findByParentIdAndRoleId(int parentId, int roleId);
}
