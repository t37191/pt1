package com.example.paper_proj.Service;

import com.example.paper_proj.Domain.Repository.UserRepository;
import com.example.paper_proj.Domain.User;
import com.example.paper_proj.Utils.JWTTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService  {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    //用户登录授权token
    public List<Object> login(String username, String password ) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken( username, password );
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername( username );
        String token=jwtTokenUtil.generateToken(userDetails);
        User user = userRepository.findByUsername(username);
        List<Object> list = new ArrayList<>();
        list.add(token);
        list.add(user.getUser_id());
        list.add(user.getAuth());
        return list;
    }

    //注册
    public User signupUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setAuth("user");
        return userRepository.save(user);
    }

    //刷新token
    public String refreshUser(String token){
        Integer flag = jwtTokenUtil.canTokenBeRefreshed(token);
        if( flag == 1){
            return jwtTokenUtil.refreshToken(token);
        }
        else if(flag == 0)
            return token;
        return null;
    }

//    //注销登录
//    public String logout(){
//
//    }

//    public User signupExpert(User user, String Dep, String Post){
//
//    }

    public User signupAdmin(User admin){
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        admin.setAuth("admin");
        return userRepository.save(admin);
    }

    //修改
    public User updateUser(User user){
        return userRepository.save(user);
    }


    //删除
    public void deleteUserById(Integer user_id){
        userRepository.deleteById(user_id);
    }

    public void deleteUserByName(String username){
        userRepository.deleteByUsername(username);
    }

    //查询
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getById(Integer user_id){
        return userRepository.findById(user_id).get();
    }

    public User getByName(String username){
        return userRepository.findByUsername(username);
    }
}
