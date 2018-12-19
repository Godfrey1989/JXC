package com.gtt.repository;

import com.gtt.entity.User;
import com.gtt.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer>,JpaSpecificationExecutor<UserRole>{

    @Query(value = "delete from t_user_role where user_id=?1",nativeQuery = true)
    @Modifying
    public void deleteByUserId(Integer id);
}

