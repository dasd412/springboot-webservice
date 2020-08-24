package com.dasd.springboot.web;

import com.dasd.springboot.domain.posts.Posts;
import com.dasd.springboot.domain.posts.PostsRepository;
import com.dasd.springboot.web.dto.PostsSaveRequestDto;
import com.dasd.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//<-MVC와 JPA모두 테스트해야 하므로
public class PostsControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown()throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    public void test_createPost()throws Exception{

        //given
        String title="test_title";
        String content="test_content";
        PostsSaveRequestDto requestDto=PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url="http://localhost:"+port+"/api/v1/posts";

        //when
        ResponseEntity<Long>responseEntity=testRestTemplate.postForEntity(url,requestDto,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts>allPosts=postsRepository.findAll();

        assertThat(allPosts.get(0).getTitle()).isEqualTo(title);
        assertThat(allPosts.get(0).getContent()).isEqualTo(content);

    }

    @Test
    public void test_updatePosts()throws  Exception{
        //given
        Posts savedPosts=postsRepository.save(Posts.builder()
        .title("title").content("content").author("author")
                .build()
        );

        Long updatedId=savedPosts.getId();
        String expectedTitle="title2";
        String expectedContent="content2";

        PostsUpdateRequestDto requestDto=PostsUpdateRequestDto.builder()
                .title(expectedTitle).content(expectedContent).build();

        String url="http://localhost:"+port+"/api/v1/posts/"+updatedId;

        HttpEntity<PostsUpdateRequestDto>requestDtoHttpEntity=new HttpEntity<>(requestDto);

        //when

        ResponseEntity<Long>responseEntity=testRestTemplate.exchange(url, HttpMethod.PUT,requestDtoHttpEntity,Long.class);

        //then

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts>all=postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);



    }

    @Test
    public void test_baseTimeEntity(){
     //given
        LocalDateTime now=LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
        .title("title").content("content").author("author")
        .build());

        //when
        List<Posts>postsList=postsRepository.findAll();

        //then

        Posts posts=postsList.get(0);

        System.out.println("createdDate="+posts.getCreatedDate()
        +", modifiedDate="+posts.getModifiedDate()
        );

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);


    }

}