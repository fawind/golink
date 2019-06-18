package accessors;

import com.google.common.collect.ImmutableList;
import exceptions.LinkExistsException;
import exceptions.LinkNotFoundException;
import models.GoLink;

public interface GoLinkDao {

  GoLink getGoLink(String alias) throws LinkNotFoundException;

  void createGoLink(GoLink goLink) throws LinkExistsException;

  void deleteGoLink(String alias) throws LinkNotFoundException;

  ImmutableList<GoLink> getGoLinks();
}
