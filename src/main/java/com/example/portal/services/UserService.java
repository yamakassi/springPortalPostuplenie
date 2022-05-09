package com.example.portal.services;

import com.example.portal.domain.User;
import com.example.portal.domain.enums.Role;
import com.example.portal.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService  {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    public PasswordEncoder passwordEncoder;
    public boolean createUser(User user){
        if(userRepo.findByEmail(user.getEmail())!=null) return  false;
        user.setActive((true));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        userRepo.save(user);
        return true;
    }
/*
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findByEmail(email);
    }*/
}
