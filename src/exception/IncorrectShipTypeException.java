package exception;

public class IncorrectShipTypeException extends Exception {
    public IncorrectShipTypeException(String message) {
        super(message);
    }

    public IncorrectShipTypeException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
