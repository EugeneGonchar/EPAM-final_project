package service.exception;

import dao.exception.dao.DAOException;

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
