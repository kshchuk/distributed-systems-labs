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

    public enum RequestType {
        AIRLINE,
        FLIGHT
    }

    private RequestMethod method;
    private RequestType type;
    private Object body;
}
