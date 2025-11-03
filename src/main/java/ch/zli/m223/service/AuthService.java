package ch.zli.m223.service;

import io.smallrye.jwt.build.Jwt;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashSet;

public class AuthService{

  public Response generateToken() {
    try {

      String token =
        Jwt.upn("jdoe@quarkus.io").issuer("https://example.com/issuer")
          .groups(new HashSet<>(Arrays.asList("User", "Admin")))
          .sign();


      return Response.ok(token).build();

    } catch (Exception e) {
      return Response.serverError().entity(e.getMessage()).build();
    }
  }
}

