package service.exception;

public class EmailInvalidException extends ServiceException {
    public EmailInvalidException() {
        super();
    }

    public EmailInvalidException(String message) {
        super(message);
    }

    public EmailInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailInvalidException(Throwable cause) {
        super(cause);
    }
}
