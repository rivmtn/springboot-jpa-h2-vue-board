package kr.fastcode.faqs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.fastcode.faqs.entity.FaqEntity;

public interface FaqRepository extends JpaRepository<FaqEntity, Long> {
    Page<FaqEntity> findAllByOrderByIdxDesc(Pageable pageable);
}
