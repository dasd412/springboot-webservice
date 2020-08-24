package com.dasd.springboot.domain.posts;

import java.util.List;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;
    
    @After//junit에서 단위 테스트가 끝날 때마다 수행되는 메소드 지정
    public void cleanUp(){
        postsRepository.deleteAll();
    }

    @Test
    public void testLoadPosts(){
        //given
        String title="test title";
        String content="test content";
  /*
        테이블 posts에 insert/update 쿼리를 실행한다.
        id값이 있으면 update, 없으면 insert가 실행됨.
         */
        postsRepository.save(Posts.builder()
        .title(title)
        .content(content)
        .author("dasd412@naver.com")
        .build());


        //when


        //테이블 posts에 있는 모든 데이터를 조회하는 메소드.
        List<Posts> postsList=postsRepository.findAll();


        //then

        Posts posts=postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }
}