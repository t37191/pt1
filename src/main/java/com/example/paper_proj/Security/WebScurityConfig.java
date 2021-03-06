package com.example.paper_proj.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebScurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userService;

    @Bean
    public JWTTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JWTTokenFilter();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService( userService ).passwordEncoder( new BCryptPasswordEncoder() );
    }

    @Override
    protected void configure( HttpSecurity httpSecurity ) throws Exception {

        httpSecurity.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/api/user/**").permitAll()
//                .antMatchers(HttpMethod.POST,"/api/user/**").permitAll()
//                .antMatchers(HttpMethod.DELETE,"/api/user/**").permitAll()
//                .antMatchers(HttpMethod.PUT,"/api/user/**").permitAll()
//                .antMatchers(HttpMethod.POST,"/api/paper").permitAll()
//                .antMatchers(HttpMethod.GET,"/api/paper/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/api/outcome/**").permitAll()
//                .antMatchers(HttpMethod.POST,"/api/outcome/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/api/paper").permitAll()
//                .antMatchers(HttpMethod.POST,"/api/keyword").permitAll()
//                .antMatchers(HttpMethod.POST).authenticated()
//                .antMatchers(HttpMethod.PUT).authenticated()
//                .antMatchers(HttpMethod.DELETE).authenticated()
//                .antMatchers(HttpMethod.GET).authenticated();

        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers().cacheControl();
    }
}
