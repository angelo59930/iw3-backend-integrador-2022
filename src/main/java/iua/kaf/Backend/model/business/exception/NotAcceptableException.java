package iua.kaf.Backend.model.business.exception;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotAcceptableException extends Exception{
  private static final long serialVersionUID = -8582277206660722157L;

  @Builder
	public NotAcceptableException(String message, Throwable ex) {
		super(message, ex);
	}

  @Builder
	public NotAcceptableException(String message) {
		super(message);
	}

  @Builder
	public NotAcceptableException(Throwable ex) {
		super(ex.getMessage(), ex);
	}
}
