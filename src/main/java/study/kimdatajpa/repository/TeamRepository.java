package study.kimdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.kimdatajpa.entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}