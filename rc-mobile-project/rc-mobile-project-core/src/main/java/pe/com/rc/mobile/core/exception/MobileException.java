package pe.com.rc.mobile.core.exception;

public class MobileException extends Exception {

	private static final long serialVersionUID = 5135225577701311106L;

	public MobileException(String message) {
		super(message);
	}

	public MobileException(String message, Throwable cause) {
		super(message, cause);
	}
}
