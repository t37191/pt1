package com.example.paper_proj.Controller;

import com.example.paper_proj.Domain.User;
import com.example.paper_proj.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:8000")
public class UserController {
    @Autowired
    private UserService userService;

    //查看所有用户
    @GetMapping(value = "/userlist")
    @PreAuthorize("hasAuthority('admin')")
    public List<User> getAll(){
        return userService.getAllUsers();
    }

    //按用户名查看
    @GetMapping(value = "/{username}")
    @PreAuthorize("hasAnyAuthority('admin,user')")
    public User getUser(@PathVariable("username") String username){
        return userService.getByName(username);
    }

    //删除用户
    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('admin')")
    public void deleteUser(@RequestParam("_type")String type,@RequestParam("_content")String content ){
        if(type.equals("byname")){
            userService.deleteUserByName(content);
        }
        else if(type.equals("byid")){
            try{
                int id = Integer.parseInt(content);
                userService.deleteUserById(id);
            }catch (Exception e){

            }
        }
    }

    //修改信息
    @PutMapping(value = "/update")
    @PreAuthorize("hasAnyAuthority('admin,user')")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }


}
