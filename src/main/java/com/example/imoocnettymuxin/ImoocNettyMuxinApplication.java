package com.example.imoocnettymuxin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.example.imoocnettymuxin.mapper")
public class ImoocNettyMuxinApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImoocNettyMuxinApplication.class, args);
    }

}
