package api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/go")
public interface RedirectService {

  @GET
  @Path("{alias}")
  Response getRedirect(@PathParam("alias") String alias);
}
