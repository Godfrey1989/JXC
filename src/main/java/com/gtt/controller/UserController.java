package com.gtt.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.gtt.entity.Menu;
import com.gtt.entity.Role;
import com.gtt.entity.User;
import com.gtt.service.MenuService;
import com.gtt.service.RoleService;
import com.gtt.service.UserService;
import com.gtt.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;

/*
* 用户Controller*/
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private MenuService menuService;

    @ResponseBody
    @RequestMapping("/login")
    public Map<String,Object> login(String imageCode, @Valid User user, BindingResult bindingResult, HttpSession session){
        Map<String,Object> map = new HashMap<String, Object>();
        if (StringUtil.isEmpty(imageCode)){
            map.put("success",false);
            map.put("errorInfo","请输入验证码");
            return map;
        }
        if (!session.getAttribute("checkcode").equals(imageCode)){
            map.put("success",false);
            map.put("errorInfo","验证码输入错误");
            return map;
        }
        if (bindingResult.hasErrors()){
            map.put("success",false);
            map.put("errorInfo",bindingResult.getFieldError().getDefaultMessage());
            return map;
        }
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassword());
        try {
            subject.login(token);
            String userName= (String) SecurityUtils.getSubject().getPrincipal();
            User currentUser=userService.findByUserName(userName);
            session.setAttribute("currentUser",currentUser);
            List<Role> roleList=roleService.findByUserId(currentUser.getId());
            map.put("roleList",roleList);
            map.put("roleSize",roleList.size());
            map.put("success",true);
            return map;
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("errorInfo","用户名或密码错误");
            return map;
        }
    }


    @ResponseBody
    @RequestMapping("/saveRole")
    public Map<String,Object> saveRole(Integer roleId, HttpSession session)throws Exception{
        Map<String,Object> map=new HashMap<String,Object>();
        Role currentRole=roleService.findById(roleId);
        session.setAttribute("currentRole",currentRole);
        map.put("success",true);
        return map;
    }

    @ResponseBody
    @GetMapping("/loadUserInfo")
    private String loadUserInfo(HttpSession session) throws Exception{
        User currentUser=(User) session.getAttribute("currentUser");
        Role currentRole=(Role) session.getAttribute("currentRole");
        return "欢迎您，"+currentUser.getTrueName()+"&nbsp;[&nbsp;"+currentRole.getName()+"&nbsp;]";
    }

    @ResponseBody
    @PostMapping("/loadMenuInfo")
    public String loadMenuInfo(HttpSession session, Integer parentId) throws Exception{
        Role currentRole=(Role) session.getAttribute("currentRole");
        return getAllMenuByParentId(parentId,currentRole.getId()).toString();

    }

    public JsonArray getAllMenuByParentId(Integer parentId, Integer roleId){
        JsonArray jsonArray=this.getMenuByParentId(parentId,roleId);
        for (int i=0;i<jsonArray.size();i++){
            JsonObject jsonObject=(JsonObject) jsonArray.get(i);
            if ("open".equals(jsonObject.get("state").getAsString())){
                continue;
            }else {
                jsonObject.add("children",getAllMenuByParentId(jsonObject.get("id").getAsInt(),roleId));
            }
        }
        return jsonArray;
    }

    public JsonArray getMenuByParentId(Integer parentId, Integer roleId){
        List<Menu> menuList=menuService.findByParentIdAndRoleId(parentId,roleId);
        JsonArray jsonArray=new JsonArray();
        for (Menu menu:menuList){
            JsonObject jsonObject=new JsonObject();
            jsonObject.addProperty("id",menu.getId());
            jsonObject.addProperty("text",menu.getName());
            if (menu.getState()==1){
                jsonObject.addProperty("state","closed");
            }else {
                jsonObject.addProperty("state","open");
            }
            jsonObject.addProperty("iconCls",menu.getIcon());
            JsonObject attributeObject=new JsonObject();
            attributeObject.addProperty("url",menu.getUrl());
            jsonObject.add("attributes",attributeObject);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
