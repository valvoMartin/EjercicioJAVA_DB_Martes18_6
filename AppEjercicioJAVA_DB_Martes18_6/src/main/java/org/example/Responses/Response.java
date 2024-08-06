package org.example.Responses;

public class Response {



    public String message;
    public boolean success;
    public Object results;

    public Response(String message, boolean success, Object results) {
        this.message = message;
        this.success = success;
        this.results = results;
    }




}
