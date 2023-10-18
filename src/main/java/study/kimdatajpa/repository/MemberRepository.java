package study.kimdatajpa.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    // 컬렉션 파라미터 바인딩 (Collection 타입으로 in절 지원)
    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);

    // 반환 타입
    List<Member> findListByUsername(String username); // 컬렉션

    Member findMemberByUsername(String username); // 단건

    Optional<Member> findOptionalByUsername(String name); // 단건 Optional

    // 스프링 데이터 JPA 페이징과 정렬
    @Query(value = "select m from Member m left join m.team t",
            countQuery = "select count(m.username) from Member m") // count 쿼리 분리 (성능 최적화)
    Page<Member> findByAge(int age, Pageable pageable);

    // 스프링 데이터 JPA 를 사용한 벌크성 수정 쿼리
    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);
}