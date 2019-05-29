package com.ljc.xdvideo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.ljc.xdvideo.mapper")
@EnableTransactionManagement
public class XdvideoApplication {


    //./ngrok -config=ngrok.cfg -subdomain 16webtest 8080
    public static void main(String[] args) {
        SpringApplication.run(XdvideoApplication.class, args);
    }

}
