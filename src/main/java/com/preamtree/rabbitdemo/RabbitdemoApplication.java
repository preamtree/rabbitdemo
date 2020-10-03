package com.preamtree.rabbitdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // 주기적으로 publish 하기 위해 넣었음. Sender.class의 send() 메소드에 붙은 어노테이션 @Scheduled 참고.
public class RabbitdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitdemoApplication.class, args);
    }

}
