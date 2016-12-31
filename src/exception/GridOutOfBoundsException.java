package exception;

public class GridOutOfBoundsException extends Exception {

    public GridOutOfBoundsException(String message) {
        super(message);
    }

    GridOutOfBoundsException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
