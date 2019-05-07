package com.framework.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.common.utils.R;
import com.framework.common.utils.RedisUtil;
import com.framework.common.utils.UserUtils;
import com.framework.common.utils.TokenUtils;
import com.framework.modules.sys.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthenticationLoginSuccessHandler implements AuthenticationSuccessHandler {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    TokenUtils tokenUtils;

    @Value("${token.expiration}")
    private Long _TIMEOUT_;

    @Value("${token.enable-redis}")
    private boolean _enable_;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        User user = UserUtils.getCurrentLoginUser();

        String token = "";
        token = tokenUtils.generateToken(user.getUsercode());

        if (_enable_) redisUtil.set(user.getUsercode(),token, _TIMEOUT_);

        out.write(objectMapper.writeValueAsString(R.ok().put("token",token).put("usercode",user.getUsercode())));
        out.flush();
        out.close();
    }
}
