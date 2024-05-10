package com.hsparklab.springbootdeveloperblog.dto;

import com.hsparklab.springbootdeveloperblog.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
    private String title;
    private String content;

    public Article toEntity(String author){
        return Article.builder()
                .title(title)
                .author(author)
                .content(content)
                .build();
    }
}
