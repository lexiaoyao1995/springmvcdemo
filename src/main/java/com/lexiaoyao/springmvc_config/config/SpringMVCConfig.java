package com.lexiaoyao.springmvc_config.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.lexiaoyao.springmvc_config.handler.ResultBodyArgumentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class SpringMVCConfig implements WebMvcConfigurer {

    //跨域设置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //针对的映射
        registry.addMapping("/**")
                //针对的origin域名
                .allowedOrigins("*")
                //针对的方法
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                //是否允许发送Cookie
                .allowCredentials(true)
                //从预检请求得到相应的最大时间,默认30分钟
                .maxAge(3600)
                //针对的请求头
                .allowedHeaders("*");
    }

    /**
     * 注册新的参数处理器
     *
     * @param resolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new ResultBodyArgumentHandler());
    }

//    /**
//     * 如果是json的MediaType，不能使用这种方式，应该使用直接注入的方式
//     *
//     * @param converters
//     */
//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(getFastJsonHttpMessageConverter());
//    }

    /**
     * 采用fastJson来处理json转换
     *
     * @return
     */
    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        return getFastJsonHttpMessageConverter();
    }

    /**
     * 定制化FastJsonHttpMessageConverter
     *
     * @return
     */
    private FastJsonHttpMessageConverter getFastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        fastJsonHttpMessageConverter.setFastJsonConfig(getFastJsonConfig());
        fastJsonHttpMessageConverter.setDefaultCharset(Charset.forName("UTF-8"));
        return fastJsonHttpMessageConverter;
    }

    private FastJsonConfig getFastJsonConfig() {
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullStringAsEmpty);
        return fastJsonConfig;
    }
}