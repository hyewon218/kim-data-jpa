package study.kimdatajpa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.kimdatajpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 스프링 데이터 JPA
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    // 스프링 데이터 JPA 로 NamedQuery 호출
    @Query(name = "Member.findByUsername") // 생략가능
    List<Member> findByUsername(@Param("username") String username);
}