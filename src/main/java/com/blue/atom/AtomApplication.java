package com.blue.atom;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.blue.*.mapper")
public class AtomApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtomApplication.class, args);
    }

}
