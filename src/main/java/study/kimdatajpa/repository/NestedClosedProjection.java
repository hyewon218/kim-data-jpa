package study.kimdatajpa.repository;

// 중첩 구조 처리
public interface NestedClosedProjection {

    String getUsername();

    TeamInfo getTeam();

    interface TeamInfo {

        String getName();
    }
}
