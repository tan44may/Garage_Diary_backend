package com.garagediary.garagediary.service.impl;
import com.garagediary.garagediary.entity.UserEntity;
import com.garagediary.garagediary.entity.enums.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class AppUserDetailsService implements UserDetailsService {
    private  final com.garagediary.garagediary.Repository.UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity user = repository.findByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException("User name not found")
        );
        Role role = user.getRole(); // enum: CUSTOMER, ADMIN, GARAGE_OWNER, TALK_TEAM

        String roleName = "ROLE_" + role.name(); // e.g. ROLE_ADMIN

        return new User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(roleName))
        );

    }
}


