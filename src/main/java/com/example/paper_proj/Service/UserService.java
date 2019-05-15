package com.example.paper_proj.Service;

import com.example.paper_proj.Domain.Admin;
import com.example.paper_proj.Domain.ComUser;
import com.example.paper_proj.Domain.Expert;
import com.example.paper_proj.Domain.Repository.AdminRepository;
import com.example.paper_proj.Domain.Repository.ComUserRepository;
import com.example.paper_proj.Domain.Repository.ExpertRepository;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
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
    private ComUserRepository comUserRepository;

    @Autowired
    private ExpertRepository expertRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    //用户登录授权token
    public String login( String username, String password ) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken( username, password );
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername( username );
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        String token=jwtTokenUtil.generateToken(userDetails);
        response.addHeader("Authorization", "Bearer " + token);
        return token;
    }

    //注册
    public ComUser signupComUser(ComUser user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setAuth("user");
        return comUserRepository.save(user);
    }

    public Expert signupExpert(ComUser user,String Dep,String Post){
        Expert expert=new Expert(user,Dep,Post);
        expert.setPassword(bCryptPasswordEncoder.encode(expert.getPassword()));
        expert.setAuth("expert");
        return expertRepository.save(expert);
    }

    public Admin signupAdmin(Admin admin){
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        admin.setAuth("admin");
        return adminRepository.save(admin);
    }

    //修改
    public ComUser updateUser(ComUser user){
        return comUserRepository.save(user);
    }

    public Admin updateAdmin(Admin admin){
        return adminRepository.save(admin);
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
