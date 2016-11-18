package exception;

public class AlreadyMarkedException extends Exception{
    public AlreadyMarkedException(String message){
        super(message);
    }

    public AlreadyMarkedException(String message, Throwable throwable){
        super(message, throwable);
    }

}
