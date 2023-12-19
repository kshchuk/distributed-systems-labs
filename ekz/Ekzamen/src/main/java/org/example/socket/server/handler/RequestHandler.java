package org.example.socket.server.handler;

import org.example.controller.EmailContactController;
import org.example.controller.PhoneContactController;
import org.example.dto.EmailContactDto;
import org.example.dto.PhoneContactDto;
import org.example.dto.Request;
import org.example.dto.Response;

import java.util.UUID;

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
                if (request.getPath().startsWith("/email/")) {
                    var id = request.getPath().substring(7);
                    return new Response(Response.ResponseStatus.SUCCESS, this.emailContactController.get(UUID.fromString(id)));
                } else if (request.getPath().startsWith("/phone/")) {
                    var id = request.getPath().substring(7);
                    return new Response(Response.ResponseStatus.SUCCESS, this.phoneContactController.get(UUID.fromString(id)));
                } else if (request.getPath().startsWith("/email/byName/")) {
                    var name = request.getPath().substring(13);
                    return new Response(Response.ResponseStatus.SUCCESS, this.emailContactController.findByFirstName(name));
                } else if (request.getPath().startsWith("/phone/byName/")) {
                    var name = request.getPath().substring(13);
                    return new Response(Response.ResponseStatus.SUCCESS, this.phoneContactController.findAllByFirstName(name));
                } else if (request.getPath().startsWith("/email/byLastName/")) {
                    var name = request.getPath().substring(15);
                    return new Response(Response.ResponseStatus.SUCCESS, this.emailContactController.findByLastName(name));
                } else if (request.getPath().startsWith("/phone/byLastName/")) {
                    var name = request.getPath().substring(15);
                    return new Response(Response.ResponseStatus.SUCCESS, this.phoneContactController.findAllByLastName(name));
                } else if (request.getPath().startsWith("/email/byFullName/")) {
                    var name = request.getPath().substring(15);
                    var names = name.split(" ");
                    return new Response(Response.ResponseStatus.SUCCESS, this.emailContactController.findByFullName(names[0], names[1]));
                } else if (request.getPath().startsWith("/phone/byFullName/")) {
                    var name = request.getPath().substring(15);
                    var names = name.split(" ");
                    return new Response(Response.ResponseStatus.SUCCESS, this.phoneContactController.findAllByFullName(names[0], names[1]));
                }
            case DELETE:
                if (request.getPath().startsWith("/email/")) {
                    var id = request.getPath().substring(7);
                    this.emailContactController.delete(UUID.fromString(id));
                    return new Response(Response.ResponseStatus.SUCCESS, "Deleted");
                } else if (request.getPath().startsWith("/phone/")) {
                    var id = request.getPath().substring(7);
                    this.phoneContactController.delete(UUID.fromString(id));
                    return new Response(Response.ResponseStatus.SUCCESS, "Deleted");
                }
            case POST:
                if (request.getPath().startsWith("/email/")) {
                    var dto = this.emailContactController.create((EmailContactDto) request.getBody());
                    return new Response(Response.ResponseStatus.SUCCESS, dto);
                } else if (request.getPath().startsWith("/phone/")) {
                    var dto = this.phoneContactController.create((PhoneContactDto) request.getBody());
                    return new Response(Response.ResponseStatus.SUCCESS, dto);
                }
            case PUT:
                if (request.getPath().startsWith("/email/")) {
                    this.emailContactController.update((EmailContactDto) request.getBody());
                    return new Response(Response.ResponseStatus.SUCCESS, "Updated");
                } else if (request.getPath().startsWith("/phone/")) {
                    this.phoneContactController.update((PhoneContactDto) request.getBody());
                    return new Response(Response.ResponseStatus.SUCCESS, "Updated");
                }
            default:
                throw new Exception("Unknown request method");

        }
    }
}
