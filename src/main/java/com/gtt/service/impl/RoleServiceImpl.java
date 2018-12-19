package com.gtt.service.impl;

import com.gtt.entity.Role;
import com.gtt.repository.RoleRepository;
import com.gtt.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.websocket.server.ServerEndpoint;
import java.util.List;


@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleRepository roleRepository;

    @Override
    public List<Role> findByUserId(Integer id){
        return roleRepository.findByUserId(id);
    }

    @Override
    public Role findById(Integer id){
        return roleRepository.findOne(id);
    }
}
