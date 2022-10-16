package iua.kaf.Backend.auth;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BadPasswordException extends Exception {
  private static final long serialVersionUID = 7414044834975725975L;

  @Builder
  public BadPasswordException(String message, Throwable ex) {
    super(message, ex);
  }

  @Builder
  public BadPasswordException(String message) {
    super(message);
  }

  public BadPasswordException(Throwable ex) {
    super(ex.getMessage(), ex);
  }

}