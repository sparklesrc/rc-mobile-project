package pe.com.rc.mobile.core.exception;

public class ServiceException extends MobileException {

	private static final long serialVersionUID = -704900615315462273L;

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
