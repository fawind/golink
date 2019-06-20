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
import models.GoLink;
import models.NewGoLink;

@Path("/api/v1/golink")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface GoLinkService {

  @GET
  ImmutableList<GoLink> getGoLinks();

  @DELETE
  @Path("{alias}")
  void deleteGoLink(@PathParam("alias") String alias);

  @POST
  void addGoLink(NewGoLink newGoLink);
}
