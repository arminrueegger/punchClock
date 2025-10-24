package ch.zli.m223.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;

import ch.zli.m223.model.Tag;
import ch.zli.m223.service.TagService;

@Path("/tags")
public class TagController {

  @Inject
  TagService tagService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(summary = "Index all Tags.", description = "Returns a list of all tags.")
  public List<Tag> index() {
    return tagService.findAll();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Creates a new tag.", description = "Creates a new tag and returns the newly added tag.")
  public Tag create(Tag tag) {
    return tagService.createTag(tag);
  }

  @PATCH
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Updates an existing tag.", description = "Updates a tag and returns the updated tag.")
  public Tag update(Tag tag) {
    return tagService.updateTag(tag);
  }

  @DELETE
  @Path("/{id}")
  @Operation(summary = "Deletes a tag.", description = "Deletes the tag with the given ID.")
  public Response delete(@PathParam("id") Long id) {
    tagService.deleteTag(id);
    return Response.ok().build();
  }
}
