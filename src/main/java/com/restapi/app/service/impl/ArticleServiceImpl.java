package com.restapi.app.service.impl;

import com.restapi.app.dao.DAO;
import com.restapi.app.dao.impl.PostgreSQLDAO;
import com.restapi.app.exceptions.CouldNotDeleteRecordException;
import com.restapi.app.exceptions.CouldNotUpdateRecordException;
import com.restapi.app.exceptions.RecordNotFoundException;
import com.restapi.app.model.request.UpdateArticleRequestModel;
import com.restapi.app.model.response.ErrorMessages;
import com.restapi.app.service.ArticleService;
import com.restapi.app.dto.ArticleDTO;
import com.restapi.app.utils.ArticleUtils;
import com.restapi.app.exceptions.CouldNotCreateRecordException;

import java.util.List;


public class ArticleServiceImpl implements ArticleService {
    DAO database;

    public ArticleServiceImpl() {
        this.database = new PostgreSQLDAO();
    }

    ArticleUtils articleUtils = new ArticleUtils();

    @Override
    public ArticleDTO createArticle(ArticleDTO article) {
        ArticleDTO returnValue = null;

        // Validate required fields
        articleUtils.validateRequiredFields(article);

        // Check if article exists
        ArticleDTO existingArticle = this.getArticleByTitle(article.getTitle());

        if (existingArticle != null) {
            throw new CouldNotCreateRecordException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }

        // Generate secure public articleId
        String articleId = articleUtils.generateUUID();
        article.setArticleId(articleId);

        // Save data to DB
        returnValue = this.saveArticle(article);

        // Return the article data
        return returnValue;
    }

    public ArticleDTO getArticleById(String id) {
        ArticleDTO returnValue = null;

        try {
            this.database.openConnection();
            returnValue = this.database.getArticleById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RecordNotFoundException(ErrorMessages.RECORD_NOT_FOUND.getErrorMessage());
        } finally {
            this.database.closeConnection();
        }

        return returnValue;
    }

    @Override
    public List<ArticleDTO> getAllArticles() {
        List<ArticleDTO> returnValue = null;

        // Connect to database
        try {
            this.database.openConnection();
            returnValue = this.database.getAllArticles();
        } finally {
            this.database.closeConnection();
        }

        return returnValue;
    }

    @Override
    public void updateArticle(ArticleDTO storedArticle, UpdateArticleRequestModel newData) {
        String newTitle = newData.getTitle();
        String newDescription = newData.getDescription();

        // Update only not empty values
        if (newTitle != null && !newTitle.isEmpty()) {
            storedArticle.setTitle(newTitle);
        }

        if (newDescription != null && !newDescription.isEmpty()) {
            storedArticle.setDescription(newDescription);
        }

        try {
            this.database.openConnection();
            this.database.updateArticle(storedArticle);
        } catch (Exception ex) {
            throw new CouldNotUpdateRecordException(ex.getMessage());
        } finally {
            this.database.closeConnection();
        }
    }

    @Override
    public void deleteArticle(ArticleDTO articleDto) {
        try {
            this.database.openConnection();
            this.database.deleteArticle(articleDto);
        } catch (Exception ex) {
            throw new CouldNotDeleteRecordException(ex.getMessage());
        } finally {
            this.database.closeConnection();
        }
    }

    private ArticleDTO getArticleByTitle(String title) {
        ArticleDTO articleDto = null;

        if (title == null || title.isEmpty()) {
            return articleDto;
        }

        // Connect to database 
        try {
            this.database.openConnection();
            articleDto = this.database.getArticleByTitle(title);
        } finally {
            this.database.closeConnection();
        }

        return articleDto;
    }

    private ArticleDTO saveArticle(ArticleDTO article) {
        ArticleDTO returnValue = null;

        // Connect to database
        try {
            this.database.openConnection();
            returnValue = this.database.saveArticle(article);
        } finally {
            this.database.closeConnection();
        }

        return returnValue;
    }
}
