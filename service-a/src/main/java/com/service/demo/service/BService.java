package com.service.demo.service;

import com.service.demo.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "serviceB", url = "http://localhost:8081"
        , fallbackFactory = BServiceFallbackFactory.class, configuration = FeignConfiguration.class)
public interface BService {

    @RequestMapping("/service/b")
    List<String> getAll();

    @RequestMapping("/service/b/{idx}")
    String getOne(@PathVariable("idx") String idx);
}
