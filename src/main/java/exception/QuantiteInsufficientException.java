package exception;

public class QuantiteInsufficientException extends Exception{
    public QuantiteInsufficientException(Double manquant) {
        super("La quantite manque de "+manquant);
    }       
}
