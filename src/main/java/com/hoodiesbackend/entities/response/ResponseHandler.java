package com.hoodiesbackend.entities.response;

import org.springframework.http.HttpStatus;

import java.util.Optional;

public class ResponseHandler {
    public static <T> Response<T> success(HttpStatus status, T response){
        return new Response.ResponseBuilder<T>()
                .setStatus(status.value())
                .setMessage(status.toString())
                .setResponse(response)
                .build();
    }

    public static <T> Response<T> fail(HttpStatus status, String message){
        return new Response.ResponseBuilder<T>()
                .setStatus(status.value())
                .setResponse(null)
                .setMessage(message)
                .build();
    }

    public static <T> Response<T> ok(T response){
        return success(HttpStatus.OK, response);
    }

    public static <T> Response<T> notFound(){
        return fail(HttpStatus.NOT_FOUND, "Resource not found");
    }
}
