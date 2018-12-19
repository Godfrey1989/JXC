package com.gtt.repository;

import com.gtt.entity.Menu;
import com.gtt.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer>{

    @Query(value = "SELECT * FROM t_menu WHERE p_id=?1 AND id IN (SELECT menu_id FROM t_role_menu WHERE role_id=?2)",nativeQuery = true)
    public List<Menu> findByParentIdAndRoleId(int parentId, int roleId);
}

