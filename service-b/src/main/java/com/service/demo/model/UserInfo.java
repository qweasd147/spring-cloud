package com.service.demo.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Getter
public class UserInfo {

    private final String username;

    public boolean isEmpty(){
        return StringUtils.isEmpty(username);
    }
}
