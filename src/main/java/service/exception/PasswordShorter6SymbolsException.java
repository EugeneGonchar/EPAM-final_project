package service.exception;

public class PasswordShorter6SymbolsException extends ServiceException {

    public PasswordShorter6SymbolsException() {
    }

    public PasswordShorter6SymbolsException(String message) {
        super(message);
    }

    public PasswordShorter6SymbolsException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordShorter6SymbolsException(Throwable cause) {
        super(cause);
    }
}
