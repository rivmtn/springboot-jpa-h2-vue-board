package kr.fastcode.faqs.controller;

import kr.fastcode.faqs.domain.Faq;
import kr.fastcode.faqs.entity.FaqEntity;
import kr.fastcode.faqs.service.FaqServiceImpl;
import kr.fastcode.faqs.util.Header;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.domain.Pageable;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/faqs")
public class FaqController {
    private final FaqServiceImpl faqService;

    @GetMapping
    public ResponseEntity<Header<List<Faq>>> faqList(
            @PageableDefault(sort = {"idx"}) Pageable pageable
    ) {
        return ResponseEntity.ok(faqService.getFaqs(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faq> getFaq(@PathVariable Long id) {
        return ResponseEntity.ok(faqService.getFaq(id));
    }

    @PostMapping
    public ResponseEntity<FaqEntity> create(@RequestBody Faq param) {
        return ResponseEntity.ok(faqService.create(param));
    }

    @PatchMapping
    public ResponseEntity<FaqEntity> update(@RequestBody Faq param) {
        return ResponseEntity.ok(faqService.update(param));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        faqService.delete(id);
    }
}