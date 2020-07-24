package pers.bookmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("pers.bookmall.order.mapper")
public class BookMallOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookMallOrderApplication.class, args);
    }
}
