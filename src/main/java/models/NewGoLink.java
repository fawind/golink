package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Objects;

public class NewGoLink {

  private final String alias;
  private final String url;

  public NewGoLink(@JsonProperty("alias") String alias, @JsonProperty("url") String url) {
    this.alias = alias;
    this.url = url;
  }

  public GoLink toGoLink() {
    return new GoLink(alias, url, new Date());
  }

  public String getAlias() {
    return alias;
  }

  public String getUrl() {
    return url;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewGoLink newGoLink = (NewGoLink) o;
    return Objects.equals(alias, newGoLink.alias) && Objects.equals(url, newGoLink.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(alias, url);
  }
}
