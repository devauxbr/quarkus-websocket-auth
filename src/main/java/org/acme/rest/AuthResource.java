package org.acme.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.security.Principal;

/**
 * Simple REST Endpoint that sends back the authenticated username
 */
@Path("rest")
public class AuthResource {

    @Inject
    Principal principal;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String get() {
        return principal.getName();
    }
}
