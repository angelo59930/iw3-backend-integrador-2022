package iua.kaf.Backend.model.business.exception;

import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FoundException extends Exception {

	private static final long serialVersionUID = -8582277206660722157L;

	@Builder
	public FoundException(String message, Throwable ex) {
		super(message, ex);
	}
	@Builder
	public FoundException(String message) {
		super(message);
	}
	@Builder
	public FoundException(Throwable ex) {
		super(ex.getMessage(), ex);
	}
}
