package com.example.ddemo.Controller;

import com.example.ddemo.Domain.Admin;
import com.example.ddemo.Domain.ComUser;
import com.example.ddemo.Domain.User;
import com.example.ddemo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/userlist")
    @PreAuthorize("hasAnyAuthority('admin','user')")
    public List<User> getAll(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasAuthority('admin')")
    public User getUser(@PathVariable("username") String username){
        return userService.getByName(username);
    }

    @PostMapping(value = "/signup-user")
    public ComUser signupUser(@RequestBody ComUser comUser){
        return userService.signupComUser(comUser);
    }

    @PostMapping(value = "/signup-admin")
    public Admin signupAdmin(@RequestBody Admin admin) {return userService.signupAdmin(admin);}

    @PostMapping(value = "/login")
    public String createToken(@RequestBody User user, HttpServletResponse response) throws AuthenticationException {
        String token=userService.login( user.getUsername(), user.getPassword()); // 登录成功会返回JWT Token给用户
        response.addHeader("Authorization", "Bearer " + token);
        return token;
    }

    @DeleteMapping(value = "/{username}")
    @PreAuthorize("hasAuthority('admin')")
    public void deleteUser(@PathVariable("username") String username){
        userService.deleteUserByName(username);
    }

    @PutMapping(value = "/update")
    @PreAuthorize("hasAuthority('admin')")
    public ComUser updateUser(@RequestBody ComUser user){
        return userService.updateUser(user);
    }
}
