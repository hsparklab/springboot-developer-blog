package com.hsparklab.springbootdeveloperblog.repository;

import com.hsparklab.springbootdeveloperblog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {

}
