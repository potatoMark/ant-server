package com.framework.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.common.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthenticationLogoutSuccessHandler implements LogoutSuccessHandler {


    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    TokenUtils tokenUtils;

    @Value("${token.header}")
    private String _TOKEN_;

    @Value("${token.enable-redis}")
    private boolean _enable_;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        String token = httpServletRequest.getHeader(_TOKEN_);
        String userCode = tokenUtils.getUsercodeFromToken(token);
        if (_enable_) redisUtil.del(userCode);
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(R.ok()));
        out.flush();
        out.close();
    }
}
