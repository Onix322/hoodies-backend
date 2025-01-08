package com.hoodiesbackend.entities.response;

import org.springframework.http.HttpStatus;

import java.util.Optional;

public class Response<T> {
    private int status;
    private String message;
    private T response;

    private Response(int status, String message, T response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public Boolean isResponsePresent(){
        return response != null;
    }

    static class ResponseBuilder<T>{
        private int status = HttpStatus.BAD_REQUEST.value();
        private String message = "";
        private T response;

        public ResponseBuilder() {}

        public ResponseBuilder<T> setResponse(T response) {
            this.response = response;
            return this;
        }

        public ResponseBuilder<T> setMessage(String message) {
            this.message = message;
            return this;
        }

        public ResponseBuilder<T> setStatus(int status) {
            this.status = status;
            return this;
        }

        public Response<T> build(){
            return new Response<>(status, message, response);
        }
    }
}
