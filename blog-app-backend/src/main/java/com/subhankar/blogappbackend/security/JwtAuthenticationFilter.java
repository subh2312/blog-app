package com.subhankar.blogappbackend.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
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
//Step 4
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenHelper tokenHelper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestToken=request.getHeader("Authorization");
        System.out.println(requestToken);
        String userName = null;
        String token =  null;
        if(requestToken!=null && requestToken.startsWith("Bearer")){
            //Sub-step1: get token
            token=requestToken.substring(7);//token without bearer
            try{
                //Sub-step 2: Get username from token
                userName=tokenHelper.getUsernameFromToken(token);
            }catch (IllegalArgumentException ie){
                System.out.println("unable to get username from token");
            }catch (ExpiredJwtException ee){
                System.out.println("Token Expired");
            }catch (MalformedJwtException me){
                System.out.println("Malformed Token");
            }
        }else {
            System.out.println("Invalid Token");
        }

        if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            //Sub-step 3:load userDetails from username
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            //Sub-step 4:validate token
            if(tokenHelper.validateToken(token,userDetails)){
                //Sub-step 5:Set spring security
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else{
                System.out.println("Invalid Token");
            }
        }else{
            System.out.println("Either username or context is null");
        }

        filterChain.doFilter(request,response);

    }
}
