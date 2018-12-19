package com.gtt.repository;

import com.gtt.entity.Role;
import com.gtt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer>{

    @Query(value = "select r.* from t_user u, t_role r, t_user_role ur where ur.user_id=u.id and ur.role_id=r.id and u.id=?1",nativeQuery = true)
    public List<Role> findByUserId(Integer id);
}

