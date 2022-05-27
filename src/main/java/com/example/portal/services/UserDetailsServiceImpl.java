package com.example.portal.services;

import com.example.portal.domain.users.User;
import com.example.portal.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public User loadUserByUsername(String email)  {
        return userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
