package springboot.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.project.entity.Board;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
