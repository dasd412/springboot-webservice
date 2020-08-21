package com.dasd.springboot.web;


import com.dasd.springboot.web.dto.HelloResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //JSON을 반환하는 컨트롤러
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDTO helloDTO(@RequestParam("name")String name,@RequestParam("amount")int amount){
        //@RequestParam은 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션이다.
        return new HelloResponseDTO(name,amount);

    }
}
