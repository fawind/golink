package accessors;

import com.google.common.collect.ImmutableList;
import java.util.Optional;
import javax.cache.Cache;
import javax.inject.Inject;
import models.GoLink;

public class GoLinkCache {

  private static final String ALL_LINKS_KEY = "all_links";
  private static final String ALIAS_KEY_PREFIX = "alias_";

  private final Cache cache;

  @Inject
  public GoLinkCache(Cache cache) {
    this.cache = cache;
  }

  public boolean containsGoLink(String alias) {
    return cache.containsKey(getAliasKey(alias));
  }

  public Optional<GoLink> getGoLink(String alias) {
    return Optional.ofNullable((GoLink) cache.get(getAliasKey(alias)));
  }

  public void addGoLink(String alias, GoLink goLink) {
    cache.put(getAliasKey(alias), goLink);
  }

  public void clearGoLink(String alias) {
    cache.remove(getAliasKey(alias));
  }

  public Optional<ImmutableList<GoLink>> getGoLinks() {
    return Optional.ofNullable((ImmutableList<GoLink>) cache.get(ALL_LINKS_KEY));
  }

  public void setGoLinks(ImmutableList<GoLink> goLinks) {
    cache.put(ALL_LINKS_KEY, goLinks);
  }

  public void clearGoLinks() {
    cache.remove(ALL_LINKS_KEY);
  }

  private String getAliasKey(String alias) {
    return ALIAS_KEY_PREFIX + alias;
  }
}
