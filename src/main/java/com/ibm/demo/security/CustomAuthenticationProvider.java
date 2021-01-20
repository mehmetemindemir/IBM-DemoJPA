package com.ibm.demo.security;

import com.ibm.demo.entity.Role;
import com.ibm.demo.entity.User;
import com.ibm.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    @Autowired
    public CustomAuthenticationProvider(UserService userService){
        this.userService=userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user=userService.getUser(name,password);
        List<Role> roles=new ArrayList<>();

        if (null!=user && null!=roles) {
            return new UsernamePasswordAuthenticationToken(user,null, null);
        } else {
            throw new BadCredentialsException("");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
