package com.ibm.demo.repository;

import com.ibm.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select count(u) from user_ibm u where u.userName=:userName and u.password=:userPassword")
    int getUserCount(
            @Param("userName") String userName,
            @Param("userPassword") String userPassword
    );
    @Query("select u from user_ibm u where u.userName=:userName and u.password=:userPassword")
    User getUserByUserNameAndPassword(
            @Param("userName") String userName,
            @Param("userPassword") String userPassword
    );
}
