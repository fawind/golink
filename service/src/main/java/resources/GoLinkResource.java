package resources;

import accessors.GoLinkDao;
import api.GoLinkService;
import com.google.common.collect.ImmutableList;
import exceptions.LinkExistsException;
import exceptions.LinkNotFoundException;
import javax.inject.Inject;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotFoundException;
import models.GoLink;
import models.NewGoLink;

public class GoLinkResource implements GoLinkService {

  private final GoLinkDao goLinkDao;

  @Inject
  public GoLinkResource(GoLinkDao goLinkDao) {
    this.goLinkDao = goLinkDao;
  }

  @Override
  public ImmutableList<GoLink> getGoLinks() {
    return goLinkDao.getGoLinks();
  }

  @Override
  public void deleteGoLink(String alias) {
    try {
      goLinkDao.deleteGoLink(alias);
    } catch (LinkNotFoundException e) {
      throw new NotFoundException(e.getMessage());
    }
  }

  @Override
  public void addGoLink(NewGoLink newGoLink) {
    try {
      goLinkDao.createGoLink(newGoLink.toGoLink());
    } catch (LinkExistsException e) {
      throw new ForbiddenException(e.getMessage());
    }
  }
}
