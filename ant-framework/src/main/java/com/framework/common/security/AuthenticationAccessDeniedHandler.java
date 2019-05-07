package com.framework.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.common.utils.R;
import com.framework.common.utils.UserUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse resp, AccessDeniedException e) throws IOException {
        //参考ExceptionTranslationFilter 180行代码，只有Authentication!=anonymous用户时，才会调用此方法
        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(R.error(602,"访问被拒绝,权限不足，请联系管理员!")));
        out.flush();
        out.close();
    }
}
