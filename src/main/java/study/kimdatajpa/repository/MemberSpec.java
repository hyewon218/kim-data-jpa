package study.kimdatajpa.repository;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import study.kimdatajpa.entity.Member;
import study.kimdatajpa.entity.Team;

// Specifications(명세) 정의
public class MemberSpec {

    public static Specification<Member> teamName(final String teamName) {
        return (root, query, builder) -> {

            if (!StringUtils.hasLength(teamName)) {
                return null;
            }
            Join<Member, Team> t = root.join("team", JoinType.INNER);// 회원과 조인
            return builder.equal(t.get("name"), teamName);
        };
    }

    public static Specification<Member> username(final String username) {
        return (root, query, builder) ->
            builder.equal(root.get("username"), username);
    }
}