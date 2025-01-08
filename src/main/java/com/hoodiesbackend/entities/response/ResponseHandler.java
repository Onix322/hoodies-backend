package com.hoodiesbackend.entities.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    public static ResponseEntity<Response> success(HttpStatus status, Object response){
        Response body = new Response.ResponseBuilder()
                .setStatus(status.value())
                .setMessage(status.toString())
                .setResult(response)
                .build();

        return ResponseEntity.status(status.value()).body(body);
    }

    public static ResponseEntity<Response> fail(HttpStatus status, String message){
        Response body = new Response.ResponseBuilder()
                .setStatus(status.value())
                .setMessage(message)
                .setResult(null)
                .build();

        return ResponseEntity.status(status.value()).body(body);
    }

    public static ResponseEntity<Response> ok(Object response){
        return success(HttpStatus.OK, response);
    }

    public static ResponseEntity<Response> notFound(){
        return fail(HttpStatus.NOT_FOUND, "Resource not found");
    }
}
