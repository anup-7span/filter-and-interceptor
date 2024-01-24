package com.hibernate2levelcache.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value=JsonInclude.Include.NON_EMPTY)
public class Response<T> {
    private Integer status;
    private T data;
    private String message;
    private Boolean error;

    public Response(Integer status, String message, Boolean error) {
        this.status = status;
        this.message = message;
        this.error = error;
    }

    public Response(Integer status, T data, String message, Boolean error) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.error = error;
    }

    public Response(T data) {
        this.data = data;
    }

    public Response() {
    }

    public Boolean getError() {
        return this.error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Integer getStatus() {
        return this.status;
    }

    public T getData() {
        return (T)this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
