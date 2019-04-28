package com.example.paper_proj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaRepositories
@ServletComponentScan
public class DdemoApplication {

    private static Logger logger = LoggerFactory.getLogger(DdemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DdemoApplication.class, args);
        logger.info("------------------启动完毕------------------");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
