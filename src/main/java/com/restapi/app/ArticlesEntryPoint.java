package com.restapi.app;

import com.restapi.app.model.request.CreateArticleRequestModel;
import com.restapi.app.model.request.UpdateArticleRequestModel;
import com.restapi.app.model.response.ArticleRest;
import com.restapi.app.model.response.DeleteArticleResponseModel;
import com.restapi.app.model.response.ResponseStatus;
import com.restapi.app.service.ArticleService;
import com.restapi.app.service.impl.ArticleServiceImpl;
import com.restapi.app.dto.ArticleDTO;
import org.springframework.beans.BeanUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/articles")
public class ArticlesEntryPoint {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArticleRest createArticle(CreateArticleRequestModel requestObject) {
        ArticleRest returnValue = new ArticleRest();

        // Prepare ArticleDTO
        ArticleDTO articleDto = new ArticleDTO();
        BeanUtils.copyProperties(requestObject, articleDto);

        // Create new article
        ArticleService articleService = new ArticleServiceImpl();
        ArticleDTO createdArticleDto = articleService.createArticle(articleDto);

        // Prepare Response
        BeanUtils.copyProperties(createdArticleDto, returnValue);

        return returnValue;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArticleRest getArticle(@PathParam("id") String id) {
        ArticleRest returnValue = new ArticleRest();

        ArticleService articleService = new ArticleServiceImpl();
        ArticleDTO articleDto = articleService.getArticleById(id);

        BeanUtils.copyProperties(articleDto, returnValue);

        return returnValue;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArticleRest> getAllArticles() {
        List<ArticleRest> returnValue = new ArrayList<ArticleRest>();

        ArticleService articleService = new ArticleServiceImpl();
        List<ArticleDTO> articles = articleService.getAllArticles();

        for (ArticleDTO article : articles) {
            ArticleRest artileModel = new ArticleRest();
            BeanUtils.copyProperties(article, artileModel);
            returnValue.add(artileModel);
        }

        return returnValue;
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArticleRest updateArticle(@PathParam("id") String id, UpdateArticleRequestModel newArticleData) {
        ArticleService articleService = new ArticleServiceImpl();
        ArticleDTO storedArticle = articleService.getArticleById(id);

        articleService.updateArticle(storedArticle, newArticleData);

        ArticleRest returnValue = new ArticleRest();
        BeanUtils.copyProperties(storedArticle, returnValue);

        return returnValue;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public DeleteArticleResponseModel deleteArticle(@PathParam("id") String id) {
        DeleteArticleResponseModel returnValue = new DeleteArticleResponseModel();

        ArticleService articleService = new ArticleServiceImpl();
        ArticleDTO storedArticle = articleService.getArticleById(id);

        articleService.deleteArticle(storedArticle);

        returnValue.setResponseStatus(ResponseStatus.SUCCESS);

        return returnValue;
    }
}