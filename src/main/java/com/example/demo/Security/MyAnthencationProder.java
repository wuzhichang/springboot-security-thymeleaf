package com.example.demo.Security;

import com.example.demo.entity.Admin;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class MyAnthencationProder implements AuthenticationProvider {

    @Autowired
    private AdminService adminService;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Admin admin = adminService.findByUsername(username);
        if(admin == null){
            throw new UsernameNotFoundException("用户不存在");
        }

        if(!passwordEncoder.matches(password, admin.getPassword())) {
            throw new BadCredentialsException("密码错误");
        }
//        if(userDetails == null) {
//            throw new UsernameNotFoundException("用户名/密码无效");
//        }else if (!userDetails.isEnabled()){
//            throw new DisabledException("用户已被禁用");
//        }else if (!userDetails.isAccountNonExpired()) {
//            throw new AccountExpiredException("账号已过期");
//        }else if (!userDetails.isAccountNonLocked()) {
//            throw new LockedException("账号已被锁定");
//        }else if (!userDetails.isCredentialsNonExpired()) {
//            throw new LockedException("凭证已过期");
//        }

        //TODO
        List<SimpleGrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority("test"));
        return new UsernamePasswordAuthenticationToken(admin, password, auths);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
