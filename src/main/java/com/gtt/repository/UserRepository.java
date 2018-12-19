package com.gtt.repository;

import com.gtt.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer>,JpaSpecificationExecutor<User>{

    @Query(value = "select * from t_user where user_name=?1",nativeQuery = true)
    public  User findByUserName(String userName);
}

