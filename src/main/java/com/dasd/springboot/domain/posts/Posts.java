package com.dasd.springboot.domain.posts;

import com.dasd.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter//클래스 내 모든 필드의 게터 자동 생성. LOMBOK
@NoArgsConstructor//기본 생성자 자동 추가. LOMBOK

@Entity//테이블과 링크될 클래스를 나타내는 어노테이션
public class Posts extends BaseTimeEntity {

    /*
    Entity클래스는 절대로 request/response 클래스로 사용해서는 안된다!
    따로 dto클래스를 만들어 연결해야 한다.
     */

    @Id//해당 테이블의 Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Primary key의 생성 규칙.
    //GenerationType.IDENTITY를 해야만 auto_increment가 된다.
    private Long id;

    @Column(length=500,nullable = false)//테이블의 칼럼을 나타내는 어노테이션. SIZE(255)->SIZE(500)으로 변경함.
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)//타입 VARCHAR->TEXT로 변경함.
    private String content;

    private String author;

    @Builder//해당 클래스의 빌더 패턴 클래스 생성. 생성자 상단에서 선언 시 생성자에 포함된 필드만 빌더에 포함됨. LOMBOK
    public Posts(String title,String content,String author){
        this.title=title;
        this.content=content;
        this.author=author;

    }


    /*
    Entity 클래스에서는 절대로 setter 메소드를 만들지 않는다.

    대신, 해당 필드의 값 변경이 필요할 경우, 명확한 목적과 의도를 나타내는 메소드를 추가하여
    이 메소드로만 사용해야 한다.

     */

    public void update(String title,String content){
        this.title=title;
        this.content=content;
    }

}
