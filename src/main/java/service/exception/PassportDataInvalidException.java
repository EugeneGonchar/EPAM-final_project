package service.exception;

public class PassportDataInvalidException extends ServiceException {
    public PassportDataInvalidException() {
        super();
    }

    public PassportDataInvalidException(String message) {
        super(message);
    }

    public PassportDataInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public PassportDataInvalidException(Throwable cause) {
        super(cause);
    }
}
