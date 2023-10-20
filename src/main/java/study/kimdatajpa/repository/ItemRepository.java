package study.kimdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.kimdatajpa.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
