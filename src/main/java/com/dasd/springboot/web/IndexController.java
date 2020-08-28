package com.dasd.springboot.web;

import com.dasd.springboot.service.posts.PostsService;
import com.dasd.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){//Model 객체는 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장한다.
        model.addAttribute("posts",postsService.findAllDesc());

        return "index";
    }
    
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts_save";
    }


    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto=postsService.findById(id);
        System.out.println("index test"+dto.getTitle()+" "+dto.getContent());
        model.addAttribute("post",dto);
        return "posts_update";
    }
}
