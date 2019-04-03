package com.framework.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.common.utils.Base64Util;
import com.framework.common.utils.R;
import com.framework.common.utils.RedisUtil;
import com.framework.common.utils.UserUtils;
import com.framework.common.utils.token.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        String token = httpServletRequest.getHeader("X-Token");
        try {
            if (TokenUtils.verifyJWT(token)) {
                String jwt = new String (Base64Util.decryptBASE64(token));
                redisUtil.del(jwt.split("\\.")[2]);
            }
        } catch (Exception e) {
            logger.error("token verify error", e);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(R.ok()));
        out.flush();
        out.close();
    }
}
