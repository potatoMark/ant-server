package com.framework.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.common.utils.Base64Util;
import com.framework.common.utils.R;
import com.framework.common.utils.RedisUtil;
import com.framework.common.utils.token.TokenUtils;
import com.framework.modules.sys.pojo.User;
import com.framework.modules.sys.service.IUserService;
import com.framework.modules.sys.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;

@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {

    private static final String ADMIN_TOKEN = "X-Token";

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    IUserService userService;

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, AuthenticationException {

        HttpServletRequest request = ((FilterInvocation)o).getHttpRequest();

        HttpServletResponse response = ((FilterInvocation)o).getHttpResponse();

        if (SecurityContextHolder.getContext().getAuthentication() == null
                || "anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {


            String _token = request.getHeader(ADMIN_TOKEN);


            boolean flg = false;
            try {

                flg = TokenUtils.verifyJWT(_token);
            }catch (Exception e) {
                logger.error("token verify error",e);
                throw new AccessDeniedException("JWT认证失败!");
            }

            // 解析异常
            if (!flg) {
                throw new AccessDeniedException("JWT认证失败");
            }

            String jwt = new String (Base64Util.decryptBASE64(_token));

            String key = jwt.split("\\.")[2];

            Long userId = (Long) redisUtil.get(key);

            User user = userService.getUser(userId);

            if (user == null) {
                throw new AccessDeniedException("权限不足");
            }

            // 生成通过认证
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // 将权限写入本次会话
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        }




    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}