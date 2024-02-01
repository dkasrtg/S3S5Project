package exception;

public class DateDebutBeforeLastDebutException extends Exception{
    public DateDebutBeforeLastDebutException() {
        super("La date de debut ne doit pas etre avant ou egal la derniere date de debut");
    }       
}
