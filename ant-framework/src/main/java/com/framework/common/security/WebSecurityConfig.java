package com.framework.common.security;

import com.framework.common.filter.AuthenticationTokenFilter;
import com.framework.modules.sys.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UrlAccessDecisionManager urlAccessDecisionManager;

    @Autowired
    AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

    @Autowired
    AuthenticationLogoutSuccessHandler authenticationLogoutSuccessHandler;

    @Autowired
    AuthenticationLoginFailureHandler authenticationLoginFailureHandler;

    @Autowired
    AuthenticationLoginSuccessHandler authenticationLoginSuccessHandler;

    @Autowired
    AuthenticationEntryPointHandler authenticationEntryPointHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * 注册 token 转换拦截器为 bean
     * 如果客户端传来了 token ，那么通过拦截器解析 token 赋予用户权限
     *
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationTokenFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>(){
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(urlAccessDecisionManager);
                        return o;
                    }
                })
                .anyRequest().permitAll()       // 允许所有请求通过
                .and()
                    // 配置被拦截时的处理
                    .exceptionHandling()
                        .authenticationEntryPoint(authenticationEntryPointHandler)   // 添加 token 无效或者没有携带 token 时的处理
                        .accessDeniedHandler(authenticationAccessDeniedHandler)      //添加无权限时的处理
                .and()
                    .formLogin()
                        .usernameParameter("userName").passwordParameter("password").permitAll()
                        .failureHandler(authenticationLoginFailureHandler)
                        .successHandler(authenticationLoginSuccessHandler)
                .and()
                    .logout()
                        .logoutSuccessHandler(authenticationLogoutSuccessHandler).permitAll()
                .and()
                    .csrf()
                        .disable()                      // 禁用 Spring Security 自带的跨域处理
                .sessionManagement()                        // 定制我们自己的 session 策略
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 调整为让 Spring Security 不创建和使用 session

        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }
}