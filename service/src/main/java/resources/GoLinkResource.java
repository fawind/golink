package resources;

import accessors.GoLinkDao;
import api.GoLinkService;
import com.google.common.collect.ImmutableList;
import exceptions.LinkExistsException;
import exceptions.LinkNotFoundException;
import java.net.URI;
import javax.inject.Inject;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import models.GoLink;
import models.NewGoLink;

public class GoLinkResource implements GoLinkService {

  private final GoLinkDao goLinkDao;

  @Inject
  public GoLinkResource(GoLinkDao goLinkDao) {
    this.goLinkDao = goLinkDao;
  }

  @Override
  public ImmutableList<GoLink> getRedirects() {
    return goLinkDao.getGoLinks();
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

  @Override
  public void deleteRedirect(String alias) {
    try {
      goLinkDao.deleteGoLink(alias);
    } catch (LinkNotFoundException e) {
      throw new NotFoundException(e.getMessage());
    }
  }

  @Override
  public void addRedirect(NewGoLink newGoLink) {
    try {
      goLinkDao.createGoLink(newGoLink.toGoLink());
    } catch (LinkExistsException e) {
      throw new ForbiddenException(e.getMessage());
    }
  }
}
