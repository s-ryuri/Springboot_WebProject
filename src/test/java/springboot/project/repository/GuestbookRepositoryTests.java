package springboot.project.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.web.WebAppConfiguration;
import springboot.project.entity.Guestbook;
import springboot.project.entity.QGuestbook;

import java.util.Optional;
import java.util.stream.IntStream;

@WebAppConfiguration
@SpringBootTest
public class GuestbookRepositoryTests {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    void 쿼리테스트(){
        IntStream.rangeClosed(1,50).forEach(i->{
            Guestbook guestbook = Guestbook.builder()
                            .title("Title..." + i)
                            .content("content..." + i)
                            .writer("user" + (i % 10))
                            .build();
            System.out.println(guestbookRepository.save(guestbook));
        });
        //guestbookRepository.save(guestbook);
    }

    @Test
    void 업데이트테스트(){
        Optional<Guestbook> result = guestbookRepository.findById(210L);

        if(result.isPresent()){
            Guestbook guestbook = result.get();
            guestbook.changeContent("change content");
            guestbook.changeTitle("changeTitle");

            guestbookRepository.save(guestbook);
        }
    }

    @Test
    void 테스트쿼리(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook; // 1

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder(); // 2

        BooleanExpression expression = qGuestbook.title.contains(keyword); // 3

        builder.and(expression); // 4

        Page<Guestbook> result = guestbookRepository.findAll(builder,pageable); // 5

        for (Guestbook guestbook : result) {
            System.out.println(guestbook);
        }
    }

    @Test
    void 다중항목검색테스트쿼리(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder(); // 2

        BooleanExpression exTitle = qGuestbook.title.contains(keyword); // 3
        BooleanExpression exContent = qGuestbook.content.contains(keyword); // 3
        BooleanExpression exAll = exTitle.or(exContent);

        builder.and(exAll);

        builder.and(qGuestbook.gno.gt(0L));

        Page<Guestbook> result = guestbookRepository.findAll(builder,pageable); // 5

        for (Guestbook guestbook : result) {
            System.out.println(guestbook);
        }
    }

}
