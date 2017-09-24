package com.xiangyu.account;

import com.xiangyu.account.config.ApplicationConfiguration;
import com.xiangyu.account.config.OpsServiceConfiguration;
import com.xiangyu.account.config.UserServiceConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplicationBuilder parentBuilder =  new SpringApplicationBuilder(ApplicationConfiguration.class);
        parentBuilder.child(UserServiceConfiguration.class).properties("server.port:8080").run(args);
        parentBuilder.child(OpsServiceConfiguration.class).properties("server.port:8081").run(args);
    }
}
