package com.restapi.app.service;

import com.restapi.app.dto.ArticleDTO;
import com.restapi.app.model.request.UpdateArticleRequestModel;

import java.util.List;

public interface ArticleService {
    ArticleDTO createArticle(ArticleDTO article);
    ArticleDTO getArticleById(String id);
    List<ArticleDTO> getAllArticles();
    void updateArticle(ArticleDTO storedArticle, UpdateArticleRequestModel newData);
    void deleteArticle(ArticleDTO articleDto);
}
