package com.hsparklab.springbootdeveloperblog.controller;

import com.hsparklab.springbootdeveloperblog.domain.Article;
import com.hsparklab.springbootdeveloperblog.dto.ArticleListViewResponse;
import com.hsparklab.springbootdeveloperblog.dto.ArticleViewResponse;
import com.hsparklab.springbootdeveloperblog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@RequiredArgsConstructor
@Controller
public class BlogViewController {
    private final BlogService blogService;
    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model){
        if(id == null){
            model.addAttribute("article", new ArticleViewResponse());
        }else{
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        return "newArticle";
    }
    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleListViewResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles);
        return "articleList";
    }
    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model){
        Article article = blogService.findById(id);
        System.out.print("getCreatedAt :" + article.getCreatedAt());
        System.out.print("getCreatedAt dto :" + new ArticleViewResponse(article).getCreatedAt());
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }
}
