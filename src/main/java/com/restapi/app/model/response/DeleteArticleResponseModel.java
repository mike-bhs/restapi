package com.restapi.app.model.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DeleteArticleResponseModel {
    private ResponseStatus responseStatus;

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
