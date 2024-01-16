package exception;

public class QuantiteInsufficientForMeubleException extends Exception{
    public QuantiteInsufficientForMeubleException(String nomMateriau,Double manquant) {
        super("La quantite de "+nomMateriau+" manque de "+manquant);
    }       
}
