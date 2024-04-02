package kr.fastcode.faqs.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import kr.fastcode.faqs.domain.Faq;
import kr.fastcode.faqs.entity.FaqEntity;
import kr.fastcode.faqs.util.Header;

public interface FaqService {
    Header<List<Faq>> getFaqs(Pageable pageable);
    Faq getFaq(Long id);
    FaqEntity create(Faq param);
    FaqEntity update(Faq param);
    void delete(Long id);   
}
