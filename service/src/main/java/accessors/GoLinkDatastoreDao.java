package accessors;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.PreparedQuery.TooManyResultsException;
import com.google.appengine.api.datastore.Query;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Streams;
import exceptions.LinkExistsException;
import exceptions.LinkNotFoundException;
import java.util.Comparator;
import java.util.Optional;
import javax.inject.Inject;
import models.GoLink;

public class GoLinkDatastoreDao implements GoLinkDao {

  private final DatastoreService datastoreService;
  private final GoLinkCache cache;

  @Inject
  public GoLinkDatastoreDao(DatastoreService datastoreService, GoLinkCache cache) {
    this.datastoreService = datastoreService;
    this.cache = cache;
  }

  @Override
  public GoLink getGoLink(String alias) throws LinkNotFoundException {
    Optional<GoLink> cachedGoLink = cache.getGoLink(alias);
    if (cachedGoLink.isPresent()) {
      return cachedGoLink.get();
    }
    Query query =
        new Query(
            DatastoreGoLinkEntity.KIND, KeyFactory.createKey(DatastoreGoLinkEntity.KIND, alias));
    PreparedQuery preparedQuery = datastoreService.prepare(query);
    try {
      Entity entity =
          Optional.ofNullable(preparedQuery.asSingleEntity())
              .orElseThrow(() -> new LinkNotFoundException(alias));
      GoLink goLink = DatastoreGoLinkEntity.getGoLinkFromEntity(entity);
      cache.addGoLink(alias, goLink);
      return goLink;
    } catch (TooManyResultsException e) {
      throw new IllegalStateException(
          String.format("More than one entity found for key %s", alias), e);
    }
  }

  @Override
  public void createGoLink(GoLink goLink) throws LinkExistsException {
    Entity entity = DatastoreGoLinkEntity.getEntityfromGoLink(goLink);
    if (cache.containsGoLink(goLink.getAlias()) || containsKey(entity.getKey())) {
      throw new LinkExistsException(goLink.getAlias());
    }
    cache.addGoLink(goLink.getAlias(), goLink);
    cache.clearGoLinks();
    datastoreService.put(entity);
  }

  @Override
  public void deleteGoLink(String alias) throws LinkNotFoundException {
    Key key = KeyFactory.createKey(DatastoreGoLinkEntity.KIND, alias);
    try {
      datastoreService.delete(key);
      cache.clearGoLink(alias);
      cache.clearGoLinks();
    } catch (IllegalArgumentException e) {
      throw new LinkNotFoundException(alias);
    }
  }

  @Override
  public ImmutableList<GoLink> getGoLinks() {
    Optional<ImmutableList<GoLink>> cachedGoLinks = cache.getGoLinks();
    if (cachedGoLinks.isPresent()) {
      return cachedGoLinks.get();
    }
    Query query = new Query(DatastoreGoLinkEntity.KIND);
    PreparedQuery preparedQuery = datastoreService.prepare(query);
    ImmutableList<GoLink> goLinks = Streams.stream(preparedQuery.asIterable())
        .map(DatastoreGoLinkEntity::getGoLinkFromEntity)
        .sorted(Comparator.comparing(GoLink::getAlias))
        .collect(ImmutableList.toImmutableList());
    cache.setGoLinks(goLinks);
    return goLinks;
  }

  private boolean containsKey(Key key) {
    Query query = new Query(DatastoreGoLinkEntity.KIND, key).setKeysOnly();
    PreparedQuery preparedQuery = datastoreService.prepare(query);
    return !preparedQuery.asList(FetchOptions.Builder.withLimit(1)).isEmpty();
  }
}
