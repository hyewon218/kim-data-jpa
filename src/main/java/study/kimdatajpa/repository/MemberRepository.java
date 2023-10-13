package study.kimdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.kimdatajpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}