package com.restapi.app.utils;

import com.restapi.app.exceptions.MissingRequiredFieldException;
import com.restapi.app.model.response.ErrorMessages;
import com.restapi.app.dto.ArticleDTO;
import java.util.UUID;

public class ArticleUtils {
    public void validateRequiredFields(ArticleDTO article) throws MissingRequiredFieldException {
        if (isBlank(article.getTitle()) ||
                isBlank(article.getDescription()) ||
                isBlank(article.getAuthorName())
        ) {
            throw new MissingRequiredFieldException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
    }

    public String generateUUID() {
        String returnValue = UUID.randomUUID().toString().replaceAll("-", "");
        return returnValue;
    }

    private boolean isBlank(String data) {
        return data == null || data.isEmpty();
    }
}
