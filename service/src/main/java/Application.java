import accessors.GoLinkDao;
import accessors.GoLinkDatastoreDao;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;
import exceptions.DefaultExceptionMapper;
import javax.inject.Singleton;
import resources.GoLinkResource;
import resources.PingResource;

public class Application extends javax.ws.rs.core.Application implements Module {

  public Application() {}

  @Override
  public void configure(Binder binder) {
    binder.bind(DefaultExceptionMapper.class);
    binder.bind(GoLinkDao.class).to(GoLinkDatastoreDao.class);
    binder.bind(PingResource.class);
    binder.bind(GoLinkResource.class);
  }

  @Provides
  @Singleton
  public DatastoreService datastoreServiceProvider() {
    return DatastoreServiceFactory.getDatastoreService();
  }
}
