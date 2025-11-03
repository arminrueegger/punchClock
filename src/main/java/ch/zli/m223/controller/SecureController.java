package ch.zli.m223.controller;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/secure")
public class SecureController {

  @GET
  @Path("/data")
  @RolesAllowed("Admin")
  public Response getSecureData() {
    return Response.ok("This is protected data.").build();
  }
}

