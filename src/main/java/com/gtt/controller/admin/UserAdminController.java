package com.gtt.controller.admin;

import com.gtt.entity.Role;
import com.gtt.entity.User;
import com.gtt.service.RoleService;
import com.gtt.service.UserRoleService;
import com.gtt.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/user")
public class UserAdminController {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private UserRoleService userRoleService;

    @RequestMapping("/list")
    public Map<String,Object> list(User user,@RequestParam(value="page",required=false)Integer page,@RequestParam(value="rows",required=false)Integer rows)throws Exception{
        Map<String,Object> resultMap=new HashMap<>();
        List<User> userList=userService.list(user, page, rows, Sort.Direction.ASC, "id");
        for(User u:userList){
            List<Role> roleList=roleService.findByUserId(u.getId());
            StringBuffer sb=new StringBuffer();
            for(Role r:roleList){
                sb.append(","+r.getName());
            }
            u.setRoles(sb.toString().replaceFirst(",", ""));
        }
        Long total=userService.getCount(user);
        resultMap.put("rows", userList);
        resultMap.put("total", total);
        return resultMap;
    }

    @RequestMapping("/save")
    public Map<String,Object> save(User user)throws Exception{
        Map<String,Object> resultMap=new HashMap<>();
        if (user.getId()==null){
         if (userService.findByUserName(user.getUserName())!=null){
             resultMap.put("success",false);
             resultMap.put("errorInfo","用户名不存在！");
             return resultMap;
         }
        }
        userService.save(user);
        resultMap.put("success",true);
        return resultMap;
    }

    @RequestMapping("/delete")
    public Map<String,Object> delete(Integer id)throws Exception{
        Map<String,Object> resultMap=new HashMap<>();
        userRoleService.deleteByUserId(id);
        userService.delete(id);
        resultMap.put("success",true);
        return resultMap;
    }
}
