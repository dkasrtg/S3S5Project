package exception;

public class QuantiteInsufficientForVenteException extends Exception{
    public QuantiteInsufficientForVenteException(String nomMeuble,Double manquant) {
        super("La quantite de "+nomMeuble+" manque de "+manquant);
    }       
}
