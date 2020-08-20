package com.service.demo.service;


import java.util.Arrays;
import java.util.List;

public class BServiceFallbackImpl implements BService{

    @Override
    public List<String> getAll() {
        return Arrays.asList("에러났지만 무시");
    }

    @Override
    public String getOne(String idx) {
        return "에러났지만 무시";
    }
}
