package exceptions;

import static com.google.common.base.Throwables.getStackTraceAsString;
import static java.lang.String.format;

import java.util.logging.Logger;
import javax.inject.Singleton;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Singleton
public class DefaultExceptionMapper implements ExceptionMapper<Throwable> {

  private static Logger log = Logger.getLogger(DefaultExceptionMapper.class.getName());

  @Override
  public Response toResponse(Throwable exception) {
    if (exception instanceof WebApplicationException) {
      log.info(
          format(
              "Handled exception: %s - Stacktrace: %s",
              exception.getMessage(), getStackTraceAsString(exception)));
      return ((WebApplicationException) exception).getResponse();
    }
    log.severe(
        format(
            "Unhandled exception: %s - Stacktrace: %s",
            exception.getMessage(), getStackTraceAsString(exception)));
    return Response.status(500).build();
  }
}
