package org.example.dto;

import lombok.Data;

@Data
public class Request implements java.io.Serializable {
    public enum RequestMethod {
        GET,
        POST,
        PUT,
        DELETE,
        PATCH,
        HEAD,
        OPTIONS,
        TRACE,
        CONNECT
    }


    private RequestMethod method;
    private String path;
    private Object body;
}
