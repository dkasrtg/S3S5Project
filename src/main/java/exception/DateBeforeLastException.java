package exception;

public class DateBeforeLastException extends Exception{
    public DateBeforeLastException() {
        super("Ne peut pas sortir avant la derniere date de sortie");
    }       
}
