package service.exception;

public class ExistEmptyFieldException extends ServiceException {

    public ExistEmptyFieldException() {
    }

    public ExistEmptyFieldException(String message) {
        super(message);
    }

    public ExistEmptyFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistEmptyFieldException(Throwable cause) {
        super(cause);
    }
}
