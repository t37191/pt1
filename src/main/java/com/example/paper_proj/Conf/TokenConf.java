package com.example.paper_proj.Conf;

public class TokenConf {
    public static final long EXPIRATION_TIME = 600;     // 5天(以毫秒ms计)
    public static final String SECRET = "JWTSecret";      // JWT密码
    public static final String TOKEN_PREFIX = "Bearer";         // Token前缀
    public static final String HEADER_STRING = "Authorization"; // 存放Token的Header Key
}
