package exception;

public class QuantiteNegatifZeroException extends Exception{
    public QuantiteNegatifZeroException() {
        super("La quantite ne doit pas etre negative ou nulle");
    }       
}
