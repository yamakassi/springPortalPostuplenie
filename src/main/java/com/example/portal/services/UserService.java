package com.example.portal.services;

import com.example.portal.domain.Application;
import com.example.portal.domain.Exam;
import com.example.portal.domain.User;
import com.example.portal.domain.enums.Role;
import com.example.portal.repositories.ExamRepo;
import com.example.portal.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService  {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ExamRepo examRepo;
    @Autowired
    public PasswordEncoder passwordEncoder;
    public boolean createUser(User user){
        if(!userRepo.findByEmail(user.getEmail()).isEmpty()) {

            return  false;
        }
        user.setActive((true));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_ADMIN);
        userRepo.save(user);
        return true;
    }
    public List<User> allUsers(){
        return userRepo.findAll();
    }
    public void disableUser(Long id){
        User user = userRepo.findById(id).orElse(null);
        if(user !=null){
            user.setActive(false);
        }
        userRepo.save(user);

    }
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepo.findByEmail(principal.getName()).get();
    }
    public void saveExams(List<Exam> exams){
       for(Exam exam : exams) {

           examRepo.save(exam);
       }
    }
    public  void addApplication(User user, Application application){
        user.setApplication(application);
        userRepo.save(user);
    }


/*
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findByEmail(email);
    }*/
}
