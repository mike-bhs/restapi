package com.restapi.app.dao.impl;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.restapi.app.dao.DAO;
import com.restapi.app.dto.ArticleDTO;
import com.restapi.app.entity.ArticleEntity;
import com.restapi.app.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.BeanUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class PostgreSQLDAO implements DAO {
    Session session;

    @Override
    public void openConnection() {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        session = sessionFactory.openSession();
    }

    @Override
    public void closeConnection() {
        if (session != null) {
            session.close();
        }
    }

    @Override
    public ArticleDTO getArticleByTitle(String title) {
        ArticleDTO articleDto = null;

        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<ArticleEntity> criteria = cb.createQuery(ArticleEntity.class);

        //Query roots always reference entity
        Root<ArticleEntity> entityRoot = criteria.from(ArticleEntity.class);
        criteria.select(entityRoot);
        criteria.where(cb.equal(entityRoot.get("title"), title));

        // Fetch single result
        Query<ArticleEntity> query = session.createQuery(criteria);
        List<ArticleEntity> resultList = query.getResultList();

        if (resultList != null && resultList.size() > 0) {
            ArticleEntity articleEntity = resultList.get(0);
            articleDto = new ArticleDTO();
            BeanUtils.copyProperties(articleEntity, articleDto);
        }

        return articleDto;    
    }

    @Override
    public ArticleDTO getArticleById(String id) {
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<ArticleEntity> criteria = cb.createQuery(ArticleEntity.class);

        //Query roots always reference entity
        Root<ArticleEntity> profileRoot = criteria.from(ArticleEntity.class);
        criteria.select(profileRoot);

        System.out.println("SEARCHING BY: " + id);
        criteria.where(cb.equal(profileRoot.get("articleId"), id));

        // Fetch single result
        ArticleEntity articleEntity = session.createQuery(criteria).getSingleResult();

        ArticleDTO returnValue = new ArticleDTO();
        BeanUtils.copyProperties(articleEntity, returnValue);

        return returnValue;
    }

    @Override
    public ArticleDTO saveArticle(ArticleDTO article) {
        ArticleDTO returnValue = null;

        ArticleEntity articleEntity = new ArticleEntity();
        BeanUtils.copyProperties(article, articleEntity);

        session.beginTransaction();
        session.save(articleEntity);
        session.getTransaction().commit();

        returnValue = new ArticleDTO();
        BeanUtils.copyProperties(articleEntity, returnValue);

        return returnValue;
    }

    @Override
    public List<ArticleDTO> getAllArticles() {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ArticleEntity> criteria = cb.createQuery(ArticleEntity.class);

        Root<ArticleEntity> userRoot = criteria.from(ArticleEntity.class);
        criteria.select(userRoot);

        List<ArticleEntity> searchResults = session.createQuery(criteria).getResultList();

        List<ArticleDTO> returnValue = new ArrayList<ArticleDTO>();
        for (ArticleEntity articleEntity : searchResults) {
            ArticleDTO articleDto = new ArticleDTO();
            BeanUtils.copyProperties(articleEntity, articleDto);
            returnValue.add(articleDto);
        }

        return returnValue;
    }

    @Override
    public void updateArticle(ArticleDTO article) {
        ArticleEntity articleEntity = new ArticleEntity();
        BeanUtils.copyProperties(article, articleEntity);

        session.beginTransaction();
        session.update(articleEntity);
        session.getTransaction().commit();
    }

    public void deleteArticle(ArticleDTO article) {
        ArticleEntity userEntity = new ArticleEntity();
        BeanUtils.copyProperties(article, userEntity);

        session.beginTransaction();
        session.delete(userEntity);
        session.getTransaction().commit();
    }
}
