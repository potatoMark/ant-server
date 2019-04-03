package com.framework.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.framework.common.utils.Base64Util;
import com.framework.common.utils.R;
import com.framework.common.utils.RedisUtil;
import com.framework.common.utils.UserUtils;
import com.framework.common.utils.token.TokenUtils;
import com.framework.modules.sys.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        User user = UserUtils.getCurrentLoginUser();

        String token = "";
        try {

          token = TokenUtils.getToken(user);

          String jwt = new String (Base64Util.decryptBASE64(token));

          String signature = jwt.split("\\.")[2];

          redisUtil.set(signature,user.getId(),60);

        }catch (Exception e) {
            logger.error("Token generate error",e);
        }
        out.write(objectMapper.writeValueAsString(R.ok().put("token",token).put("usercode",user.getUsercode())));
        out.flush();
        out.close();
    }
}
