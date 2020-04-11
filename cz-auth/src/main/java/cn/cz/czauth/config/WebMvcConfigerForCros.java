package cn.cz.czauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvcConfigerForCros {
    /**
     * 配置跨域解决方案
     * @return
     */
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("PUT", "GET","POST","DELETE","PATCH")
                        .allowedHeaders("*")
                        .exposedHeaders("header1", "header2")
                        .allowCredentials(true);
            }
        };
    }
}
