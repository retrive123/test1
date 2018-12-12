package io.github.jhipster.application.service.dto;

public class ResponseDetails {
    public Boolean isValid;
    public String message;

    public ResponseDetails(Boolean isValid , String message) {
        this.isValid = isValid;
        this.message = message;
    }
}