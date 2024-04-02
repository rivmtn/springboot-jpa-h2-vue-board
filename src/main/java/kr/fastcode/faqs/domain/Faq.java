package kr.fastcode.faqs.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Faq {
    private Long idx;
    private String title;
    private String contents;
    private String author;
    private String createdAt;
}
