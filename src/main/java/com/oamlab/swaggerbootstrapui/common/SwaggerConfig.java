package com.oamlab.swaggerbootstrapui.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: Andy Yao
 * @Description: Swagger2的接口配置
 * @Date: 2022/11/8 20:01
 * @params:
 * @return:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Value("${config.swaggerConfig.isShow}")
    private Boolean isShow;


//    @Bean
//    public Docket createUserRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .enable(isShow)
//                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
//                .apiInfo(apiInfo())
//                .groupName("用户管理API")
//                // 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现
//                .select()
//                //.apis(RequestHandlerSelectors.basePackage("com.oamlab.swaggerbootstrapui.controller.user"))
//                // 扫描所有有注解的api
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                // 扫描指定包中的swagger注解
//                //.apis(RequestHandlerSelectors.basePackage("com.kxjl.belleps.api"))
//                // 扫描所有
//                //.apis(RequestHandlerSelectors.any())
//                // 对所有路径进行监控
//                .paths(PathSelectors.any())
//                .build();
//    }

    @Bean
    public Docket getHealthyRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(isShow)
                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo())
                .groupName("API状态")
                // 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.oamlab.swaggerbootstrapui.controller.key"))
                // 扫描所有有注解的api
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 扫描指定包中的swagger注解
                //.apis(RequestHandlerSelectors.basePackage("com.kxjl.belleps.api"))
                // 扫描所有 .apis(RequestHandlerSelectors.any())
                // 对所有路径进行监控
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket createRoleRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(isShow)
                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo())
                .groupName("秘钥管理API")
                // 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.oamlab.swaggerbootstrapui.controller.key"))
                // 扫描所有有注解的api
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 扫描指定包中的swagger注解
                //.apis(RequestHandlerSelectors.basePackage("com.kxjl.belleps.api"))
                // 扫描所有 .apis(RequestHandlerSelectors.any())
                // 对所有路径进行监控
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo() {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                // 设置标题
                .title("STS，Secret Transfer Service")
                // 描述
                .description("STS，Secret Transfer Service & 基于 ysocean，建议部署于集群内（接口无鉴权），可用于业务环境配置项加密，和基础设施环境交付前的接口测试、压力测试。")
                // 作者信息
                .contact(new Contact("https://github.com/oamlab & Andy Yao & QQ:77810419", "https://github.com/oamlab", "77810419@qq.com"))
                // 版本
                .version("版本号:" + "0.1")
                .build();
    }
}
