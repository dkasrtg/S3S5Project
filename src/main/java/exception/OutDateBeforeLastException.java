package exception;

public class OutDateBeforeLastException extends Exception{
    public OutDateBeforeLastException() {
        super("Ne peut pas sortir avant la derniere date de sortie");
    }       
}
