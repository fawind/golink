import accessors.GoLinkDao;
import accessors.GoLinkDatastoreDao;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import exceptions.DefaultExceptionMapper;
import java.util.Collections;
import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;
import javax.inject.Singleton;
import resources.GoLinkResource;
import resources.PingResource;
import resources.RedirectResource;

public class Application extends javax.ws.rs.core.Application implements Module {

  public Application() {}

  @Override
  public void configure(Binder binder) {
    binder.bind(DefaultExceptionMapper.class);
    binder.bind(GoLinkDao.class).to(GoLinkDatastoreDao.class);
    binder.bind(PingResource.class);
    binder.bind(GoLinkResource.class);
    binder.bind(RedirectResource.class);
  }

  @Provides
  @Singleton
  public DatastoreService datastoreServiceProvider() {
    return DatastoreServiceFactory.getDatastoreService();
  }

  @Provides
  @Singleton
  public Cache cacheProvider() throws CacheException {
    CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
    return cacheFactory.createCache(Collections.emptyMap());
  }
}
