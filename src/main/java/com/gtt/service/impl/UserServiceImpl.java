package com.gtt.service.impl;

import com.gtt.entity.Role;
import com.gtt.entity.User;
import com.gtt.repository.RoleRepository;
import com.gtt.repository.UserRepository;
import com.gtt.service.RoleService;
import com.gtt.service.UserService;
import com.gtt.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<User> list(User user, Integer page, Integer pageSize, Sort.Direction direction, String...properties){
        Pageable pageable=new PageRequest(page-1,pageSize);
        Page<User> pageUser=userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate=criteriaBuilder.conjunction();
                if (user!=null){
                    if (StringUtil.isNotEmpty(user.getUserName())){
                        predicate.getExpressions().add(criteriaBuilder.like(root.get("userName"),"%"+user.getUserName()+"%"));
                    }
                    predicate.getExpressions().add(criteriaBuilder.notEqual(root.get("id"),1));
                }
                return predicate;
            }
        },pageable);
        return pageUser.getContent();
    }
    @Override
    public Long getCount(User user){
        Long count=userRepository.count(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate=criteriaBuilder.conjunction();
                if (user!=null){
                    if (StringUtil.isNotEmpty(user.getUserName())){
                        predicate.getExpressions().add(criteriaBuilder.like(root.get("userName"),"%"+user.getUserName()+"%"));
                    }
                    predicate.getExpressions().add(criteriaBuilder.notEqual(root.get("id"),1));
                }
                return predicate;
            }
        });
        return count;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.delete(id);
    }
}
