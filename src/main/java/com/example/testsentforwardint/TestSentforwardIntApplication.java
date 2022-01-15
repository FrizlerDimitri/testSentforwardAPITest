package com.example.testsentforwardint;

import com.oth.sentforward.persistence.entities.EmailAccount;
import com.oth.sentforward.persistence.entities.MasterAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestSentforwardIntApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(TestSentforwardIntApplication.class, args);
    }


    @Autowired
    TestService testService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

          testService.testRestService1();
          testService.testRestService2();

    }
}
