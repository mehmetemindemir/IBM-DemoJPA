package com.ibm.demo.service;

import com.ibm.demo.entity.Role;
import com.ibm.demo.entity.User;
import com.ibm.demo.repository.RoleRepository;
import com.ibm.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Autowired
    public UserService(UserRepository userRepository,RoleRepository roleRepository){
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
    }

    public boolean checkUserLogin(String userName, String password){
        boolean res=false;
        try{
            if(userRepository.getUserCount(userName,password)>0){
                res=true;
            }
        }catch (Exception e){
            res=false;
        }
       return res;
    }
    public User getUser(String userName, String password){
        User user=null;
        try {
            user=userRepository.getUserByUserNameAndPassword(userName,password);
        }catch (Exception e){
            user=null;
        }
        return user;
    }
    public List<Role> getUserRole(String userName){
        List<Role> roles=null;
        try {
            roles=roleRepository.getUserRoles(userName);
        }catch (Exception e){
            roles=null;
        }
        return roles;
    }


}
