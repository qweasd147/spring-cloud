package com.service.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/service/b")
public class BController {


    @GetMapping
    public List<String> findAll(){
        return Arrays.asList("1","2","3");
    }

    @GetMapping("/{idx}")
    public String findOne(@PathVariable String idx){


        return "find "+idx;
    }
}
