package cn.cz.czbase;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("cn.cz.czbase.dao")
public class CzBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CzBaseApplication.class, args);
    }

}
