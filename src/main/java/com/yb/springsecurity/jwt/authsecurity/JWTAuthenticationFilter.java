package com.yb.springsecurity.jwt.authsecurity;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yangbiao
 * @Description:验证其他请求token是否合法的类 OncePerRequestFilter继承GenericFilterBean了, 并扩展了内容
 * @date 2018/11/19
 */
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        //获取请求token认证通过返回的Authentication信息
        Authentication authentication = TokenAuthenticationService.getAuthentication(request);
        //把Authentication信息set到SecurityContext里
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //执行过滤
        filterChain.doFilter(request, response);
    }
}
