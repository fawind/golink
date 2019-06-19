package api;

import com.google.common.collect.ImmutableList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import models.GoLink;
import models.NewGoLink;

@Path("/go")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface GoLinkService {

  @GET
  ImmutableList<GoLink> getRedirects();

  @GET
  @Path("{alias}")
  Response getRedirect(@PathParam("alias") String alias);

  @DELETE
  @Path("{alias}")
  void deleteRedirect(@PathParam("alias") String alias);

  @POST
  void addRedirect(NewGoLink newGoLink);
}
