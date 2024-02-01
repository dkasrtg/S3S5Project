package exception;

public class DateFinAfterDateDebutException extends Exception{
    public DateFinAfterDateDebutException() {
        super("La date fin ne doit pas etre avant ou egal au date dabut");
    }       
}
