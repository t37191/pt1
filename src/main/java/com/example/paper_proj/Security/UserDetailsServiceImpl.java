package com.example.paper_proj.Security;

import com.example.paper_proj.Domain.Repository.UserRepository;
import com.example.paper_proj.Domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
//    private UserRepository userRepository;
//
//    public UserDetailsServiceImpl(UserRepository userRepository){
//        this.userRepository=userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//
//        User user = userRepository.findByUsername(s);
//        if (user == null) {
//            throw new UsernameNotFoundException("用户不存在");
//        }
//        return user;
//    }
private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}
