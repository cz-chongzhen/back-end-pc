package cn.cz.czupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CzUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(CzUploadApplication.class, args);
    }

}
