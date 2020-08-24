package com.dasd.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass//JPA Entity 클래스들이 이 어노테이션이 부착한 클래스를 상속할 경우, 필드들도 칼럼으로 인식시킨다.
@EntityListeners(AuditingEntityListener.class)//Auditing 기능을 포함시켜서 생성 시간과 변경 시간을 자동으로 업데이트시킨다.
public abstract class BaseTimeEntity {

    @CreatedDate//Entity가 생성되어 저장될 때의 시간이 자동 저장된다.
    private LocalDateTime createdDate;

    @LastModifiedDate//조회한 Entity의 값을 변경할 떄 시간이 자동 저장된다.
    private LocalDateTime modifiedDate;

}
