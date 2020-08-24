package com.dasd.springboot.web.dto;

import com.dasd.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class PostsSaveRequestDto {

    /*
    뷰를 위한 클래스. 변경 가능성이 매우 높다.
    반면 entity 클래스는 변경 가능성이  적다.
    따라서 dto 클래스와 entity클래스는 분리시켜야 한다.

     */


    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }








    public Posts toEntity() {
        return Posts.builder().title(title).content(content).author(author).build();
    }
}
