package com.gtt.service.impl;

import com.gtt.entity.Menu;
import com.gtt.repository.MenuRepository;
import com.gtt.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("menuService")
public class MenuServiceImpl implements MenuService{

    @Resource
    private MenuRepository  menuRepository;

    @Override
    public List<Menu> findByParentIdAndRoleId(int parentId, int roleId){
        return menuRepository.findByParentIdAndRoleId(parentId,roleId);
    }
}
