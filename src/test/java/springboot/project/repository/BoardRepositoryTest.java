package springboot.project.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springboot.project.entity.Board;
import springboot.project.entity.Member;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void 게시글삽입(){
        IntStream.rangeClosed(1,100).forEach(i->{
            Member member = Member.builder().email("user" + i + "@aaa.com").build();

            Board board = Board.builder()
                    .title("title.." + i)
                    .content("content..." + i)
                    .member(member)
                    .build();

            boardRepository.save(board);
        });
    }

    @Test
    @Transactional
    void 멤버불러오기(){
        Optional<Board> result = boardRepository.findById(20L);

        Board board = result.get();

        System.out.println("================board==============");
        System.out.println(board);
        System.out.println("================board==============");
        System.out.println(board.getMember().getName());


    }
}