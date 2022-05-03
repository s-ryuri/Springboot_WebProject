package springboot.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springboot.project.dto.GuestbookDTO;
import springboot.project.dto.PageRequestDTO;
import springboot.project.dto.PageResultDTO;
import springboot.project.entity.Guestbook;

@SpringBootTest
public class GuestbookServiceTests {

    @Autowired
    private GuestbookService service;

    @Test
    void testRegister(){
        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("sampel Title")
                .content("sample contet...")
                .writer("user0")
                .build();

        System.out.println(service.register(guestbookDTO));
    }

    @Test
    void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

        for (GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
            System.out.println(guestbookDTO);
        }
    }
}
