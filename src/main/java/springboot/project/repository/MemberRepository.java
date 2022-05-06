package springboot.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.project.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
