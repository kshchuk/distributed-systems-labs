package org.example.socket.server.handler;

import org.example.controller.EmailContactController;
import org.example.controller.PhoneContactController;
import org.example.dto.Request;
import org.example.dto.Response;

public class RequestHandler {
    private final EmailContactController emailContactController;
    private final PhoneContactController phoneContactController;

    public RequestHandler(EmailContactController emailContactController, PhoneContactController phoneContactController) {
        this.emailContactController = emailContactController;
        this.phoneContactController = phoneContactController;
    }

    public Response handle(Request request) throws Exception {
        switch (request.getMethod()) {
            case GET:
            case DELETE:
            case PATCH:
                break;
            case HEAD:
                break;
            case OPTIONS:
                break;
            case TRACE:
                break;
            case CONNECT:
                break;
            default:
                throw new Exception("Unknown request method");

        }
        return new Response(Response.ResponseStatus.ERROR, "Not found");
    }
}
