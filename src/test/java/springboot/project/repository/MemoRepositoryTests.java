package springboot.project.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import springboot.project.entity.Memo;

import java.util.List;

@SpringBootTest
public class MemoRepositoryTests {

    @Autowired
    MemoRepository memoRepository;

    @Test
    @Transactional
    void testClass(){
        Long mno = 100L;

        Memo result = memoRepository.getOne(mno);
        System.out.println("=============================");
        System.out.println(result);
    }

    @Test
    void 페이지테스트(){
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Memo> result = memoRepository.findAll(pageable);
        System.out.println("Total Page : " + result.getTotalPages());
        System.out.println("Total Count : " + result.getTotalElements());
        System.out.println("Page Number : " + result.getNumber());
        System.out.println("Page Size : " + result.getSize());
        System.out.println("Has next page ? : " + result.hasNext());
        System.out.println("first page ? : " + result.isFirst());

        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }

    @Test
    void 정렬테스트(){
        Sort sort1 = Sort.by("mno").descending();
        Sort sort2 = Sort.by("memoText").ascending();
        Sort sortAll = sort1.and(sort2);
        PageRequest pageable = PageRequest.of(0, 10, sortAll);
        Page<Memo> result = memoRepository.findAll(pageable);

        result.get().forEach(memo ->{
            System.out.println(memo);
        });
    }

    @Test
    void 쿼리메서드테스트(){
        List<Memo> byMnoBetweenOrderByMnoDesc = memoRepository.findByMnoBetweenOrderByMnoDesc(17L, 34L);
        for (Memo memo : byMnoBetweenOrderByMnoDesc) {
            System.out.println(memo);
        }
    }

    @Test
    void 쿼리메서드와페이지어블(){
        Sort sort1 = Sort.by("mno").descending();

        Pageable pageable = PageRequest.of(0, 10, sort1);

        Page<Memo> result = memoRepository.findByMnoBetween(10L, 50L, pageable);

        result.get().forEach(memo -> System.out.println(memo));
    }

    @Test
    @Transactional
    @Commit
    void 삭제테스트(){
        memoRepository.deleteMemoByMnoLessThan(10L);
    }

    @Test
    void 쿼리페이징테스트(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").ascending());
        Page<Memo> listWithQuery = memoRepository.getListWithQuery(5L, pageable);
        listWithQuery.get().forEach(memo -> System.out.println(memo));
    }
}
