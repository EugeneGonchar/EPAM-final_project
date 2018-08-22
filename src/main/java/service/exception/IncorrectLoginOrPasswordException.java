package service.exception;

public class IncorrectLoginOrPasswordException extends ServiceException {
    public IncorrectLoginOrPasswordException() {
    }

    public IncorrectLoginOrPasswordException(String message) {
        super(message);
    }

    public IncorrectLoginOrPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectLoginOrPasswordException(Throwable cause) {
        super(cause);
    }
}
