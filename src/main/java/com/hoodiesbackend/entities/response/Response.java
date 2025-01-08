package com.hoodiesbackend.entities.response;

import org.springframework.http.HttpStatus;

public class Response {
    private int status;
    private String message;
    private Object result;

    private Response(int status, String message, Object result) {
        this.status = status;
        this.message = message;
        this.result = result;
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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }

    static class ResponseBuilder {
        private int status = HttpStatus.BAD_REQUEST.value();
        private String message = "";
        private Object result;

        public ResponseBuilder() {
        }

        public ResponseBuilder setResult(Object result) {
            this.result = result;
            return this;
        }

        public ResponseBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public ResponseBuilder setStatus(int status) {
            this.status = status;
            return this;
        }

        public Response build() {
            return new Response(status, message, result);
        }
    }
}
