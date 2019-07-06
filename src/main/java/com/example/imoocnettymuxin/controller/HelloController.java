package com.example.imoocnettymuxin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xianpeng.xia
 * on 2019-07-06 13:16
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello muxin ...";
    }
}
