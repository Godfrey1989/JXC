package com.gtt.service;

import apple.laf.JRSUIConstants;
import com.gtt.entity.Role;
import com.gtt.entity.User;
import org.springframework.data.domain.Sort;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface UserService {

    public User findByUserName(String userName);

    public List<User> list(User user, Integer page, Integer pageSize, Sort.Direction direction, String...properties);

    public Long getCount(User user);

    public void save(User user);

    public void delete(Integer id);
}
