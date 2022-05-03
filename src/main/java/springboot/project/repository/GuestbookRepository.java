package springboot.project.repository;

import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import springboot.project.entity.Guestbook;

public interface GuestbookRepository extends JpaRepository<Guestbook,Long>, QuerydslPredicateExecutor<Guestbook> {

}
