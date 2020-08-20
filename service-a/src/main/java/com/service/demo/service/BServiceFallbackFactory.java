package com.service.demo.service;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BServiceFallbackFactory implements FallbackFactory<BService> {

    @Override
    public BService create(Throwable cause) {

        log.error("외부 서비스 호출 중 에러 감지", cause);

        return new BServiceFallbackImpl();
    }
}
