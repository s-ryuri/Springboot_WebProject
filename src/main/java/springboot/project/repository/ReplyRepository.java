package springboot.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.project.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
}
