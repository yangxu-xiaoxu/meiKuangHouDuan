package com.javakc.servicebase.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 调用restful风格的web服务
 * @author leesiyu
 * @version V1.0
 * @Copyright © 北京汇才同飞教育科技公司
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();

    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("汇才同飞-学院中心API文档")
                .description("学院中心微服务接口定义")
                .version("1.1")
                .contact(new Contact("leesiyu", "http://javakc.com", "leesiyu@aliyun.com"))
                .build();
    }

}