package com.example.paper_proj.Conf;

public class TokenConf {
    public static final long EXPIRATION_TIME = 600;     // 30s
    public static final long REFRESH_TIME = 180;        // 10s
    public static final String SECRET = "JWTSecret";      // JWT密码
    public static final String TOKEN_PREFIX = "Bearer";         // Token前缀
    public static final String HEADER_STRING = "Authorization"; // 存放Token的Header Key
}
