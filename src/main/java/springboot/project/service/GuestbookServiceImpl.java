package springboot.project.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.project.dto.GuestbookDTO;
import springboot.project.dto.PageRequestDTO;
import springboot.project.dto.PageResultDTO;
import springboot.project.entity.Guestbook;
import springboot.project.repository.GuestbookRepository;

import java.util.function.Function;

@Service
@Transactional(readOnly = true)
@Log4j2
@RequiredArgsConstructor
public class GuestbookServiceImpl implements GuestbookService{

    private final GuestbookRepository guestbookRepository;

    @Override
    @Transactional
    public Long register(GuestbookDTO dto) {

        log.info("DTO================");
        log.info(dto);

        Guestbook entity = dtoToEntity(dto);
        log.info(entity);

        guestbookRepository.save(entity);

        return entity.getGno();
    }

    @Override
    public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());

        Page<Guestbook> result = guestbookRepository.findAll(pageable);

        Function<Guestbook,GuestbookDTO> fn = (entity -> entityToDto(entity));

        return new PageResultDTO<>(result,fn);
    }

    @Override
    public Guestbook dtoToEntity(GuestbookDTO dto) {
        return GuestbookService.super.dtoToEntity(dto);
    }

    @Override
    public GuestbookDTO entityToDto(Guestbook entity) {
        return GuestbookService.super.entityToDto(entity);
    }
}
