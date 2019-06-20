package resources;

import accessors.GoLinkDao;
import api.RedirectService;
import exceptions.LinkNotFoundException;
import java.net.URI;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import models.GoLink;

public class RedirectResource implements RedirectService {

  private final GoLinkDao goLinkDao;

  @Inject
  public RedirectResource(GoLinkDao goLinkDao) {
    this.goLinkDao = goLinkDao;
  }

  @Override
  public Response getRedirect(String alias) {
    try {
      GoLink goLink = goLinkDao.getGoLink(alias);
      return Response.seeOther(URI.create(goLink.getUrl())).build();
    } catch (LinkNotFoundException e) {
      throw new NotFoundException(e.getMessage());
    }
  }
}
