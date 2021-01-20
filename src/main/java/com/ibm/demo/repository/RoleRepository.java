package com.ibm.demo.repository;

import com.ibm.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query("select r from role_ibm r where r.userName=:userName")
    List<Role> getUserRoles(@Param("userName") String userName);
}
