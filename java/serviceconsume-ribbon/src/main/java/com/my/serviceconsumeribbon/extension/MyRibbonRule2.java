package com.my.serviceconsumeribbon.extension;

import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ExcludeComponentScan
public class MyRibbonRule2 {

    @Bean
    public IRule bestAvailableRule() {
        return new BestAvailableRule();
    }
}
