package com.example.paper_proj.Controller;

import com.example.paper_proj.Domain.User;
import com.example.paper_proj.Service.OutcomeService;
import com.example.paper_proj.Service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.elasticsearch.common.collect.Tuple;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:8000")
public class ApiController {
    @Autowired
    private OutcomeService outcomeService;

    @Autowired
    private UserService userService;

    private JSONArray list;

    //查询服务
    @GetMapping("/search")
    public String searchOutcomes(@RequestParam("_type")String type,
                                        @RequestParam("_content")String content,
                                        @RequestParam("_start")Integer start,
                                        @RequestParam("_limit")Integer limit){
        if(start > list.size())
            return new JSONArray().toString();
        return list.subList(start,Math.min(start+limit,list.size())).toString();
    }

    @GetMapping("/search/count")
    public int searchOutcomesCounts(@RequestParam("_type")String type,
                                        @RequestParam("_content")String content) throws IOException, JSONException {
        if(type.equals("keywords")){
            list = outcomeService.getByKeyWords(content);
        }
        else if(type.equals("author")){
            list = outcomeService.getByAuthor(content);
        }
        else if(type.equals("titab")){
            list = outcomeService.getByTitle(content);
        }
        else {
            list = new JSONArray();
        }
        return list.size();
    }

    //论文详情页
    @GetMapping("/paper")
    public String getOutcome(@RequestParam("id")String id) throws IOException {
        return outcomeService.getOutcome(id);
    }

    //获得专家详情
    @GetMapping("/expert")
    public String getExpert(@RequestParam("id")String id) throws IOException{
        return outcomeService.getExpert(id);
    }

    //用户登陆
    @PostMapping("/user/login")
    public String userLogin(@RequestBody User user) throws AuthenticationException, JSONException {
        try{
            Tuple<String,Integer> tuple =userService.login( user.getUsername(), user.getPassword());
            String token = tuple.v1();
            Integer user_id = tuple.v2();
            String username = user.getUsername();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token",token);
            jsonObject.put("user_id",user_id);
            jsonObject.put("user_name",username);
            return jsonObject.toString();
        }
        catch (Exception e){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token","");
            jsonObject.put("msg","用户名或密码错误");
            return jsonObject.toString();
        }
    }

    //用户注册
    @PostMapping("/user/signup-user")
    public String userSignup(@RequestBody User user) throws JSONException {
        try{
            userService.signupUser(user);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status","success");
            jsonObject.put("msg","注册成功");
            return jsonObject.toString();
        }catch (Exception e){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status","error");
            jsonObject.put("msg","该用户名已被注册");
            return jsonObject.toString();
        }
    }

    //刷新token
    @GetMapping("/user/refreshtoken")
    public String tokenRefresh(@RequestParam("_token") String token){
        return userService.refreshUser(token);
    }
}
