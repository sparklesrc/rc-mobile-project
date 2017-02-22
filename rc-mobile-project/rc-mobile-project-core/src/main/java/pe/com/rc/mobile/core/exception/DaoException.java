package pe.com.rc.mobile.core.exception;

public class DaoException extends MobileException {

	private static final long serialVersionUID = -5775586062999192828L;

	public DaoException(String message) {
		super(message);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}
}
