package com.dasd.springboot.service.posts;

import com.dasd.springboot.domain.posts.Posts;
import com.dasd.springboot.domain.posts.PostsRepository;
import com.dasd.springboot.web.dto.PostListResponseDto;
import com.dasd.springboot.web.dto.PostsResponseDto;
import com.dasd.springboot.web.dto.PostsSaveRequestDto;
import com.dasd.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor//final이 선언된 모든 필드를 인자값으로 하는 생성자 생성. Lombok
@Service
public class PostsService {
    /*

    서비스 계층은 트랜잭션과
    도메인 간의 순서만 보장해준다.
     */

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(long id, PostsUpdateRequestDto requestDto) {

        //트랜잭션 안에서 데이터 베이스에서 데이터를 가져오면, 이 데이터는 영속성 컨텍스트가 유지된 상태이다.
        Posts posts=postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        //이 상태에서 해당 데이터 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영한다.
        posts.update(requestDto.getTitle(),requestDto.getContent());



        return id;
    }

    public PostsResponseDto findById(long id) {
        Posts entity=postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);


    }

    @Transactional(readOnly = true)//조회 기능만 남겨두어 조회 속도가 개선된다.
    public List<PostListResponseDto> findAllDesc(){

        return postsRepository.findAllDesc().stream().map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts=postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        postsRepository.delete(posts);

    }

}
