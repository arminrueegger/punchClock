package ch.zli.m223.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.model.Category;
import ch.zli.m223.service.CategoryService;

@Path("/categories")
@Tag(name = "Categories", description = "Handling of categories")
public class CategoryController {

  @Inject
  CategoryService categoryService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(summary = "Index all Categories.", description = "Returns a list of all categories.")
  public List<Category> index() {
    return categoryService.findAll();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Creates a new category.", description = "Creates a new category and returns the newly added category.")
  public Category create(Category category) {
    return categoryService.createCategory(category);
  }

  @PATCH
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Updates an existing category.", description = "Updates a category and returns the updated category.")
  public Category update(Category category) {
    return categoryService.updateCategory(category);
  }

  @DELETE
  @Path("/{id}")
  @Operation(summary = "Deletes a category.", description = "Deletes the category with the given ID.")
  public Response delete(@PathParam("id") Long id) {
    categoryService.deleteCategory(id);
    return Response.ok().build();
  }
}
