package com.dasd.springboot.configure.auth;

import com.dasd.springboot.configure.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {



        /*

        @LoginUser 어노테이션이 붙어있고, 해당 파라미터가 SessionUser 객체라면 true를 반환하는 메서드.

         */

        boolean isLoginUserAnnotation=parameter.getParameterAnnotation(LoginUser.class)!=null;

        boolean isUserClass= SessionUser.class.equals(parameter.getParameterType());


        return isLoginUserAnnotation&&isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {


        /*
        supportsParameter()메서드가 true를 반환하면 이 메소드가 리턴값을 반환한다.
         */

        return httpSession.getAttribute("user");
    }
}
