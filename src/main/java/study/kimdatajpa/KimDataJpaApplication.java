package study.kimdatajpa;

import java.util.Optional;
import java.util.UUID;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 스프링 데이터 JPA Auditing 사용
@SpringBootApplication
public class KimDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KimDataJpaApplication.class, args);
    }

    @Bean // 등록자, 수정자를 처리해주는 AuditorAware 스프링 빈 등록
    public AuditorAware<String> auditorProvider() {
        // 실무에서는 세션 정보나, 스프링 시큐리티 로그인 정보에서 ID를 받음
        return () -> Optional.of(UUID.randomUUID().toString());
    }
}
