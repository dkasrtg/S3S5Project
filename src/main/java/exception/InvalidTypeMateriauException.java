package exception;

public class InvalidTypeMateriauException extends Exception{
    public InvalidTypeMateriauException() {
        super("Ce materiau n est pas associe ce type");
    }       
}
