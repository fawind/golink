package exceptions;

public class LinkExistsException extends Exception {

  private final String alias;

  public LinkExistsException(String alias) {
    super(String.format("There is another link for the alias: %s", alias));
    this.alias = alias;
  }

  public String getAlias() {
    return alias;
  }
}
