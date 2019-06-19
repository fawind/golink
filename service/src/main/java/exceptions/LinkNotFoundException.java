package exceptions;

public class LinkNotFoundException extends Exception {

  private final String alias;

  public LinkNotFoundException(String alias) {
    super(String.format("No link found for the alias: %s", alias));
    this.alias = alias;
  }

  public String getAlias() {
    return alias;
  }
}
