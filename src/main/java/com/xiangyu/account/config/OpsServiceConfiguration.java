package com.xiangyu.account.config;

import com.xiangyu.account.controller.OpsController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class OpsServiceConfiguration {

    @Bean
    public OpsController controller() {
        return new OpsController();
    }
}
