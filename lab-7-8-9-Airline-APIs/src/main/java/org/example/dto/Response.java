package org.example.dto;

import lombok.Data;

@Data
public class Response implements java.io.Serializable {
    public enum ResponseStatus {
        SUCCESS,
        ERROR
    }

    private ResponseStatus status;
    private Object body;
}
