package com.example.paper_proj.Controller;

import com.example.paper_proj.Domain.Admin;
import com.example.paper_proj.Domain.ComUser;
import com.example.paper_proj.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class TestController {

    @Autowired
    private UserService authService;


    @RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/user-page", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('admin','user')")
    public String userPage() {
        return "user-page";
    }

    @RequestMapping(value = "/admin-page", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('admin')")
    public String adminPage() {
        return "admin-page";
    }

    @RequestMapping(value = "/authentication/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping("/403")
    public String forbidden() {
        return "403";
    }

//     登录
    @RequestMapping(value = "/authentication/login", method = RequestMethod.POST)
    public String createToken(String username, String password, HttpServletResponse response) throws AuthenticationException {
        String token=authService.login( username, password ); // 登录成功会返回JWT Token给用户
        response.addHeader("Authorization", "Bearer " + token);
        return token;
    }

    // 注册
    @RequestMapping(value = "/authentication/register", method = RequestMethod.POST)
    public ComUser register(@RequestBody ComUser addedUser ) throws AuthenticationException {
        return authService.signupComUser(addedUser);
    }

    @RequestMapping(value = "/authentication/register2", method = RequestMethod.POST)
    public Admin register2(@RequestBody Admin addedUser ) throws AuthenticationException {
        return authService.signupAdmin(addedUser);
    }

    @PreAuthorize("hasAnyAuthority('user','admin')")
    @RequestMapping( value="/normal/test", method = RequestMethod.GET )
    public String test1() {
        return "ROLE_NORMAL /normal/test接口调用成功！";
    }

    // 测试管理员权限
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping( value = "/admin/test", method = RequestMethod.GET )
    public String test2() {
        return "ROLE_ADMIN /admin/test接口调用成功！";
    }
}
