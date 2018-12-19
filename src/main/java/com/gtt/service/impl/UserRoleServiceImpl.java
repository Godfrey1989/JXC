package com.gtt.service.impl;

import com.gtt.repository.UserRoleRepository;
import com.gtt.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("userRoleService")
@Transactional
public class UserRoleServiceImpl implements UserRoleService{

    @Resource
    private UserRoleRepository userRoleRepository;


    @Override
    public void deleteByUserId(Integer id){
        userRoleRepository.deleteByUserId(id);
    }
}
