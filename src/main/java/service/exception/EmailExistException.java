package service.exception;

import dao.exception.user.DAOException;

public class EmailExistException extends DAOException {
    public EmailExistException() {
    }

    public EmailExistException(String message) {
        super(message);
    }

    public EmailExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailExistException(Throwable cause) {
        super(cause);
    }
}
