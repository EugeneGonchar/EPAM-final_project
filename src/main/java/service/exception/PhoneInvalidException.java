package service.exception;

public class PhoneInvalidException extends ServiceException{
    public PhoneInvalidException() {
        super();
    }

    public PhoneInvalidException(String message) {
        super(message);
    }

    public PhoneInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneInvalidException(Throwable cause) {
        super(cause);
    }
}
