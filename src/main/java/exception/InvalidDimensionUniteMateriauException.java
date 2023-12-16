package exception;

public class InvalidDimensionUniteMateriauException extends Exception{
    public InvalidDimensionUniteMateriauException() {
        super("Ce materiau n a pas cette unite<->dimension");
    }       
}
