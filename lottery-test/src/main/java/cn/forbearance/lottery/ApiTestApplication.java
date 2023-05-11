package cn.forbearance.lottery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @author cristina
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class ApiTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiTestApplication.class, args);
    }

}
