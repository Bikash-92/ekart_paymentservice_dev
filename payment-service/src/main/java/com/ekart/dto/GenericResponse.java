package com.ekart.dto;


import java.util.List;

public class GenericResponse {

    private String message;
    private List<Object> errors;
    private Object object;
    private String statusCode;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setObject(Object object) {this.object = object; }


    @Override
    public String toString() {
        return "GenericResponse{" +
                "message='" + message + '\'' +
                ", errors=" + errors +
                ", object=" + object +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}
