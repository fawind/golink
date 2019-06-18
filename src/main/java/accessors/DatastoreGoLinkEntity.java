package accessors;

import com.google.appengine.api.datastore.Entity;
import java.util.Date;
import models.GoLink;

class DatastoreGoLinkEntity {
  static final String KIND = "golink_v01";
  static final String ALIAS_PROPERTY = "alias";
  static final String URL_PROPERTY = "url";
  static final String CREATED_PROPERTY = "created";

  static GoLink getGoLinkFromEntity(Entity entity) {
    return new GoLink(
        (String) entity.getProperty(ALIAS_PROPERTY),
        (String) entity.getProperty(URL_PROPERTY),
        (Date) entity.getProperty(CREATED_PROPERTY));
  }

  static Entity getEntityfromGoLink(GoLink goLink) {
    Entity entity = new Entity(KIND, goLink.getAlias());
    entity.setProperty(ALIAS_PROPERTY, goLink.getAlias());
    entity.setProperty(URL_PROPERTY, goLink.getUrl());
    entity.setProperty(CREATED_PROPERTY, goLink.getCreated());
    return entity;
  }

  private DatastoreGoLinkEntity() {}
}
