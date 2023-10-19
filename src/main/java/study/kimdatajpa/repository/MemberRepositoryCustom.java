package study.kimdatajpa.repository;

import java.util.List;
import study.kimdatajpa.entity.Member;

public interface MemberRepositoryCustom {
    List<Member> findMemberCustom();
}