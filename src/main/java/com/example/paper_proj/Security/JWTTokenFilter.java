package com.example.paper_proj.Security;

import com.example.paper_proj.Conf.TokenConf;
import com.example.paper_proj.Utils.JWTTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTTokenFilter extends OncePerRequestFilter {
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String authHeader = request.getHeader( TokenConf.HEADER_STRING );
        if (authHeader != null && authHeader.startsWith( TokenConf.TOKEN_PREFIX )) {
            final String authToken = authHeader.substring( TokenConf.TOKEN_PREFIX.length() );
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    //TODO:刷新token
//                    if(jwtTokenUtil.canTokenBeRefreshed(authToken)){
//                        String refreshedToken=jwtTokenUtil.refreshToken(authToken);
//                    }
                }
            }
        }
        chain.doFilter(request, response);
    }
}
