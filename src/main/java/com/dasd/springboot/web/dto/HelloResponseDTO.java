package com.dasd.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter//선언된 모든 필드의 get 메소드 생성해줌
@RequiredArgsConstructor//선어된 모든 final 필드가 포함된 생성자 생성해줌. final이 없는 것은 포함안됨.
public class HelloResponseDTO {

    private final String name;
    private final int amount;

}
