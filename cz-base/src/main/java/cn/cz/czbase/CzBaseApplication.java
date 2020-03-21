package cn.cz.czbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CzBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CzBaseApplication.class, args);
    }

}
