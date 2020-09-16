package com.zuul.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class ZuulContextConfig extends ZuulFilter {

    private final ResourceServerTokenServices tokenServices;
    private static final String TOKEN_HEADER = "Bearer ";

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {

        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String token = request.getHeader("Authorization");

        if(StringUtils.isEmpty(token))  return false;

        return token.startsWith(TOKEN_HEADER) && !Objects.isNull(getClaims());
    }

    @Override
    public Object run() throws ZuulException {

        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String tokenHeaderVal = request.getHeader("Authorization");

        if(StringUtils.isEmpty(tokenHeaderVal))  return null;

        final String accessToken = tokenHeaderVal.substring(TOKEN_HEADER.length());
        final String userName;

        try{
            OAuth2Authentication claims = this.tokenServices.loadAuthentication(accessToken);
            userName = claims.getName();
        }catch (RuntimeException ex){
            log.info("토큰 파싱 에러 토큰 만료됬을 가능성이 제일 높음. {}", accessToken);
            return null;
        }

        RequestContext.getCurrentContext()
                .addZuulRequestHeader("username", userName);

        return null;
    }

    private OAuth2Authentication getClaims(){

        return (OAuth2Authentication) SecurityContextHolder
                .getContext()
                .getAuthentication();
    }

}
