package study.kimdatajpa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import study.kimdatajpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 스프링 데이터 JPA
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);
}