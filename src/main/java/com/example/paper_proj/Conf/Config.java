package com.example.paper_proj.Conf;

public class Config {
    public static final long EXPIRATION_TIME = 6000;     // 10min
    public static final long REFRESH_TIME = 180;        // 3min
    public static final String SECRET = "JWTSecret";      // JWT密码
    public static final String TOKEN_PREFIX = "Bearer";         // Token前缀
    public static final String HEADER_STRING = "Authorization"; // 存放Token的Header Key

    public static final String HOST_NAME = "localhost"; //elastic的主机名
}
