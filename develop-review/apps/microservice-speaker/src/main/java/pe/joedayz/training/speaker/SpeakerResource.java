package pe.joedayz.training.speaker;

import io.quarkus.panache.common.Sort;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/speakers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SpeakerResource {


  @GET
  @Operation(summary = "Retrieve the list of speakers")
  @APIResponse(responseCode = "200")
  public List<Speaker> getSpeakers(@DefaultValue("id") @QueryParam("sortBy") String sortBy,
      @DefaultValue("0") @QueryParam("pageIndex") int pageIndex,
      @DefaultValue("25") @QueryParam("pageSize") int pageSize) {
    return Speaker
        .findAll(Sort.by(filterSortBy(sortBy)))
        .page(pageIndex, pageSize)
        .list();
  }

  @POST
  @Transactional
  @Operation(summary = "Adds a new speaker")
  @APIResponse(
      responseCode = "201",
      headers = {
          @Header(
              name = "id",
              description = "ID of the created entity",
              schema = @Schema(implementation = Integer.class)
          ),
          @Header(
              name = "location",
              description = "URI of the created entity",
              schema = @Schema(implementation = String.class)
          ),
      },
      description = "Entity successfully created"
  )
  public Response createSpeaker(Speaker newSpeaker, @Context UriInfo uriInfo) {
    newSpeaker.persist();

    return Response.created(generateUriForSpeaker(newSpeaker, uriInfo))
        .header("id", newSpeaker.id)
        .build();
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public void deleteSpeaker(@PathParam("id") Long id) {
    if (!Speaker.deleteById(id)) {
      throw new NotFoundException();
    }
  }

  private URI generateUriForSpeaker(Speaker speaker, UriInfo uriInfo) {
    return uriInfo.getAbsolutePathBuilder().path("/{id}").build(speaker.id);
  }

  private String filterSortBy(String sortBy) {
    if (!sortBy.equals("id") && !sortBy.equals("name")) {
      return "id";
    }

    return sortBy;
  }
}
