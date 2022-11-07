package iua.kaf.Backend.model.business.exception;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ForbiddenException extends Exception {

  private static final long serialVersionUID = -8582277206660722157L;

  @Builder
  public ForbiddenException(String message, Throwable ex) {
    super(message, ex);
  }

  @Builder
  public ForbiddenException(String message) {
    super(message);
  }

  @Builder
  public ForbiddenException(Throwable ex) {
    super(ex.getMessage(), ex);
  }

}
