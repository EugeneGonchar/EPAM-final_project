package service.exception;

public class DateInvalidException extends ServiceException{
    public DateInvalidException() {
        super();
    }

    public DateInvalidException(String message) {
        super(message);
    }

    public DateInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public DateInvalidException(Throwable cause) {
        super(cause);
    }
}
