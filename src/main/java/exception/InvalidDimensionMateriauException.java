package exception;

public class InvalidDimensionMateriauException extends Exception{
    public InvalidDimensionMateriauException() {
        super("Ce materiau n a pas cette dimension");
    }       
}
