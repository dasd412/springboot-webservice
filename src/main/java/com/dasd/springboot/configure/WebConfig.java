package com.dasd.springboot.configure;

import com.dasd.springboot.configure.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        /*
        HandlerMethodArgumentResolver 구현체를 스프링에서 인식할 수 있게 하는 메서드
         */
        resolvers.add(loginUserArgumentResolver);//<-LoginUserArgumentResolver를 스프링에서 인식할 수 있게 한다.
    }
}
