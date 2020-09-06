package com.dasd.springboot.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing//JPA auditing 기능 활성화
public class JpaConfig {
}
