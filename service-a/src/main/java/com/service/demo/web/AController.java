package com.service.demo.web;

import com.service.demo.service.BService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/service/a")
@RequiredArgsConstructor
public class AController {

    private final BService bService;


    @GetMapping
    public List<String> findAll(){

        return this.bService.getAll();
    }

    @GetMapping("/one")
    public String findOne(){

        return this.bService.getOne("temp");
    }
}
