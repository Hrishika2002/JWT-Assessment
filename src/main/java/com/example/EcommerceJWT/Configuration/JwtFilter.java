package com.example.EcommerceJWT.Configuration;

import com.example.EcommerceJWT.Services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Configuration // among the other beans this should be the first priority
public class JwtFilter extends GenericFilterBean {
    private TokenService tokenService;
    public JwtFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse res,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) res;
        String token = httpServletRequest.getHeader("Authorization");
        if("OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod())) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            return ;
        }
        //specific APIs without token
        if(allowRequestWithoutToken(httpServletRequest)) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(req,res);
        }
        else {
            Integer userId = new Integer(tokenService.getUserIdToken(token));
            httpServletRequest.setAttribute("userId", userId);
            filterChain.doFilter(req,res);
        }
    }
    public boolean allowRequestWithoutToken(HttpServletRequest httpServletRequest){
        String[] matches=new String[]
                {"/login","/registration","/products","/categories"};
        for(String s:matches) {
            if (httpServletRequest.getRequestURI().contains(s))
                return true;
        }
        return false;
    }
}