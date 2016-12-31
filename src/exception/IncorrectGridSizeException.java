package exception;

public class IncorrectGridSizeException extends Exception {
    public IncorrectGridSizeException(String message) {
        super(message);
    }

    IncorrectGridSizeException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
