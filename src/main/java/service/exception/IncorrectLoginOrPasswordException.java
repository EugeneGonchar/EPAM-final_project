package service.exception;

import dao.exception.user.DAOException;

public class IncorrectLoginOrPasswordException extends DAOException {
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
