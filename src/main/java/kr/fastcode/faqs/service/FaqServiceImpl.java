package kr.fastcode.faqs.service;

import kr.fastcode.faqs.domain.Faq;
import kr.fastcode.faqs.entity.FaqEntity;
import kr.fastcode.faqs.repository.FaqRepository;
import kr.fastcode.faqs.util.Header;
import kr.fastcode.faqs.util.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Slf4j
@RequiredArgsConstructor
@Service
public class FaqServiceImpl implements FaqService {

    private final FaqRepository faqRepository;

    @Override
    public Header<List<Faq>> getFaqs(Pageable pageable) {
        List<Faq> dtos = new ArrayList<>();

        Page<FaqEntity> faqEntities = faqRepository.findAllByOrderByIdxDesc(pageable);
        for (FaqEntity entity : faqEntities) {
            Faq dto = Faq.builder()
                    .idx(entity.getIdx())
                    .author(entity.getAuthor())
                    .title(entity.getTitle())
                    .contents(entity.getContents())
                    .createdAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                    .build();

            dtos.add(dto);
        }

        Pagination pagination = new Pagination(
                (int) faqEntities.getTotalElements()
                , pageable.getPageNumber() + 1
                , pageable.getPageSize()
                , 10
        );

        return Header.OK(dtos, pagination);
    }
    @Override
    public Faq getFaq(Long id) {
        FaqEntity entity = faqRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        return Faq.builder()
                .idx(entity.getIdx())
                .title(entity.getTitle())
                .contents(entity.getContents())
                .author(entity.getAuthor())
                .createdAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")))
                .build();
    }
    @Override
    public FaqEntity create(Faq param) {
        FaqEntity entity = FaqEntity.builder()
                .title(param.getTitle())
                .contents(param.getContents())
                .author(param.getAuthor())
                .createdAt(LocalDateTime.now())
                .build();
        return faqRepository.save(entity);
    }
    @Override
    public FaqEntity update(Faq param) {
        FaqEntity entity = faqRepository.findById(param.getIdx()).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        entity.setTitle(param.getTitle());
        entity.setContents(param.getContents());
        return faqRepository.save(entity);
    }
    @Override
    public void delete(Long id) {
        FaqEntity entity = faqRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        faqRepository.delete(entity);
    }
}