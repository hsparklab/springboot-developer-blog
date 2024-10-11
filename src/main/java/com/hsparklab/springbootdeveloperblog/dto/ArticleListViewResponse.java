package com.hsparklab.springbootdeveloperblog.dto;

import com.hsparklab.springbootdeveloperblog.domain.Article;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleListViewResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    public ArticleListViewResponse (Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getTitle();
        this.createdAt = article.getCreatedAt();
    }

}
