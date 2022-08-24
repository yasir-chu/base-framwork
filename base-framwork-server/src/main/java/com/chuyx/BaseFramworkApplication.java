package com.chuyx;


import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.dtp.core.spring.EnableDynamicTp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author chuyx
 * @data 2021-05-04
 */
@EnableApolloConfig({"application", "threadpool"})
@EnableSwagger2
@EnableDynamicTp
@ComponentScan(basePackages = {"com.chuyx.*"})
@SpringBootApplication
public class BaseFramworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseFramworkApplication.class, args);
    }
}
