package com.my.serviceconsumeribbon.extension;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ExcludeComponentScan
public class MyRibbonRule {

    @Bean
    public IRule randomRule() {
        return new RandomRule();
    }
}
