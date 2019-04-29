package com.searchapp.core;

import org.springframework.http.HttpStatus;

public class SearchException extends RuntimeException {
    private final HttpStatus httpsStatus;

    public SearchException(HttpStatus badGateway, String message) {
        super(message);
        this.httpsStatus = badGateway;
    }

    public HttpStatus getHttpsStatus() {
        return httpsStatus;
    }

}
