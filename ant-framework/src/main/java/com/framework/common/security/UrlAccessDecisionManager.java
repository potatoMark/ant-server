package com.framework.common.security;

import com.framework.common.utils.Base64Util;
import com.framework.common.utils.FilterUtils;
import com.framework.common.utils.RedisUtil;
import com.framework.common.utils.TokenUtils;
import com.framework.modules.sys.pojo.User;
import com.framework.modules.sys.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {

    private static final String ADMIN_TOKEN = "X-Token";

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    IUserService userService;


    @Autowired
    FilterUtils filterUtils;

    @Autowired
    TokenUtils tokenUtils;

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, AuthenticationException {

        HttpServletRequest request = ((FilterInvocation)o).getHttpRequest();

        HttpServletResponse response = ((FilterInvocation)o).getHttpResponse();

        String requestUrl = ((FilterInvocation) o).getRequestUrl();

        if (filterUtils.getApis() != null) {

            for (String openApi : filterUtils.getApis()) {
                if (requestUrl.startsWith(openApi)) {
                    return;
                }
            }
        }

        if (SecurityContextHolder.getContext().getAuthentication() == null
                || "anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {


            String _token = request.getHeader(ADMIN_TOKEN);

            if (StringUtils.isBlank(_token)) {
                throw new AccessDeniedException("JWT认证失败");
            }

            String userCode = tokenUtils.getUsercodeFromToken(_token);

            if (StringUtils.isBlank(userCode)) {
                throw new AccessDeniedException("JWT认证失败");
            }



            User user = userService.getUserByUserCode(userCode);

            if (user == null) {

                throw new AccessDeniedException("JWT认证失败");
            }

            if (!tokenUtils.validateToken(_token, user)) {

                throw new AccessDeniedException("JWT认证失败");
            }

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