package com.restapi.app.model.request;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CreateArticleRequestModel {
    private String title;
    private String description;
    private String authorName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
