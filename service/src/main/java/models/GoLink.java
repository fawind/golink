package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class GoLink implements Serializable {

  private final String alias;
  private final String url;
  private final Date created;

  public GoLink(
      @JsonProperty("alias") String alias,
      @JsonProperty("url") String url,
      @JsonProperty("created") Date created) {
    this.alias = alias;
    this.url = url;
    this.created = created;
  }

  public String getAlias() {
    return alias;
  }

  public String getUrl() {
    return url;
  }

  public Date getCreated() {
    return created;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GoLink goLink = (GoLink) o;
    return Objects.equals(alias, goLink.alias)
        && Objects.equals(url, goLink.url)
        && Objects.equals(created, goLink.created);
  }

  @Override
  public int hashCode() {
    return Objects.hash(alias, url, created);
  }
}
