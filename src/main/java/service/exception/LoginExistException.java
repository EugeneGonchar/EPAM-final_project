package service.exception;

public class LoginExistException extends ServiceException {
    public LoginExistException() {
    }

    public LoginExistException(String message) {
        super(message);
    }

    public LoginExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginExistException(Throwable cause) {
        super(cause);
    }
}
