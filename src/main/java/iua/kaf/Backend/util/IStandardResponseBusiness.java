package iua.kaf.Backend.util;

import org.springframework.http.HttpStatus;

public interface IStandardResponseBusiness {
	
	public StandardResponse build(HttpStatus httpStatus, Throwable ex, String message);

}
