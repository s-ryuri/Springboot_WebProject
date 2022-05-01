package springboot.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.project.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo,Long> {
}
