package exception;

public class DateAfterNowException extends Exception{
    public DateAfterNowException() {
        super("Date cannot be after the current date.");
    }       
}
