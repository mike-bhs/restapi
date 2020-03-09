package com.restapi.app.dao;

import com.restapi.app.dto.ArticleDTO;

import java.util.List;

public interface DAO {
    void openConnection();
    void closeConnection();

    ArticleDTO getArticleByTitle(String title);
    ArticleDTO getArticleById(String id);
    ArticleDTO saveArticle(ArticleDTO article);
    List<ArticleDTO> getAllArticles();
    void updateArticle(ArticleDTO article);
    void deleteArticle(ArticleDTO article);
}
