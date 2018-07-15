package service.exception;

public class PasswordsUnequalException extends ServiceException {

    public PasswordsUnequalException() {
    }

    public PasswordsUnequalException(String message) {
        super(message);
    }

    public PasswordsUnequalException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordsUnequalException(Throwable cause) {
        super(cause);
    }
}
