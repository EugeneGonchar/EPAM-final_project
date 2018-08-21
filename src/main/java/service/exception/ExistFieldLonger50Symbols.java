package service.exception;

public class ExistFieldLonger50Symbols extends Exception{

    public ExistFieldLonger50Symbols() {
        super();
    }

    public ExistFieldLonger50Symbols(String message) {
        super(message);
    }

    public ExistFieldLonger50Symbols(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistFieldLonger50Symbols(Throwable cause) {
        super(cause);
    }

    protected ExistFieldLonger50Symbols(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
