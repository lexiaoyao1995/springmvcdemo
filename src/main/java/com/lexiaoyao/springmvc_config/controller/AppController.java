package com.lexiaoyao.springmvc_config.controller;

import com.lexiaoyao.springmvc_config.domain.Person;
import com.lexiaoyao.springmvc_config.pojo.Result;
import com.lexiaoyao.springmvc_config.pojo.annotations.ResultBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping
    public Result getPerson() {
        return Result.success(new Person("ff"));
    }

    @PostMapping
    public void post(@ResultBody Person person) {
        System.out.println(person);
    }

}