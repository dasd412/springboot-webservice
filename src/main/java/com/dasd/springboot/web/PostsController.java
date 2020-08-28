package com.dasd.springboot.web;


import com.dasd.springboot.service.posts.PostsService;
import com.dasd.springboot.web.dto.PostsResponseDto;
import com.dasd.springboot.web.dto.PostsSaveRequestDto;
import com.dasd.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor//final이 선언된 모든 필드를 인자값으로 하는 생성자 생성. Lombok
@RestController
public class PostsController {

    private final PostsService postsService;


    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable long id, @RequestBody PostsUpdateRequestDto requestDto){

        System.out.println("post"+requestDto.getTitle()+" "+requestDto.getContent());
        return postsService.update(id,requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable long id ){
        return postsService.findById(id);
    }


    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable long id){
        postsService.delete(id);
        return id;
    }

}
