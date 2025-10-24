package ch.zli.m223.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.dto.EntryDto;
import ch.zli.m223.model.Entry;
import ch.zli.m223.service.EntryService;

@Path("/entries")
@Tag(name = "Entries", description = "Handling of entries")
public class EntryController {

  @Inject
  EntryService entryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all Entries.", description = "Returns a list of all entries.")
    public List<Entry> index() {
        return entryService.findAll();
    }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Creates a new entry.", description = "Creates a new entry and returns the newly added entry.")
  public EntryDto create(EntryDto entryDto) {
    Entry entry = entryDto.toDomain();
    Entry createdEntry = entryService.createEntry(entry);
    return createdEntry.toDto();
  }

  @PATCH
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(summary = "Updates an existing entry.", description = "Updates an entry and returns the updated entry.")
  public EntryDto update(EntryDto entryDto) {
    Entry entry = entryDto.toDomain();
    Entry updatedEntry = entryService.updatesEntry(entry);
    return updatedEntry.toDto();
  }

  @DELETE
  @Path("/{id}")
  @Operation(summary = "Deletes an entry.", description = "Deletes the entry with the given ID.")
  public Response delete(@PathParam("id") Long id) {
    entryService.deleteEntry(id);
    return Response.ok().build();
  }
}
