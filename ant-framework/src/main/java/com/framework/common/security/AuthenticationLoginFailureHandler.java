package com.framework.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.common.utils.R;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthenticationLoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter out = httpServletResponse.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {

            out.write(objectMapper.writeValueAsString(R.error(604,"用户名/密码错误")));
        } else if (e instanceof DisabledException) {
            out.write(objectMapper.writeValueAsString(R.error(604,"账户被禁用,请联系管理员!")));
        } else {
            out.write(objectMapper.writeValueAsString(R.error(604,"登录异常,请联系管理员")));
        }
        out.flush();
        out.close();
    }
}
