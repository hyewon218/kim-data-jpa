package study.kimdatajpa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.kimdatajpa.dto.MemberDto;
import study.kimdatajpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 스프링 데이터 JPA
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    // 스프링 데이터 JPA 로 NamedQuery 호출
    @Query(name = "Member.findByUsername") // 생략가능
    List<Member> findByUsername(@Param("username") String username);

    // 메서드에 JPQL 쿼리 작성
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    // 단순히 값 하나를 조회
    @Query("select m.username from Member m")
    List<String> findUsernameList();

    // DTO 로 직접 조회
    @Query("select new study.kimdatajpa.dto.MemberDto(m.id, m.username, t.name) " +
        "from Member m join m.team t")
    List<MemberDto> findMemberDto();

}