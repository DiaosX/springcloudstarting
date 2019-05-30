package com.my.serviceconsumeribbon;

import com.my.serviceconsumeribbon.extension.ExcludeComponentScan;
import com.my.serviceconsumeribbon.extension.MyRibbonRule;
import com.my.serviceconsumeribbon.extension.MyRibbonRule2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {ExcludeComponentScan.class})})
@EnableEurekaClient
@EnableSwagger2
@RibbonClients(value = {
        @RibbonClient(name = "serviceprovider", configuration = MyRibbonRule.class),
        @RibbonClient(value = "serviceprovider1", configuration = MyRibbonRule2.class)})
public class ServiceconsumeRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceconsumeRibbonApplication.class, args);
    }


    /*
    Spring Cloud Ribbon是一个基于HTTP和TCP的客户端负载均衡工具，
    它基于Netflix Ribbon实现。通过Spring Cloud的封装，
    可以让我们轻松地将面向服务的REST模版请求自动转换成客户端负载均衡的服务调用。
     */

    /**
     * 向spring注入开启负载均衡的RestTemplate
     *
     * @return
     */

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
