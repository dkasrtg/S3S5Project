package exception;

public class QuantiteInsuffisantExcpetion extends Exception{
    public QuantiteInsuffisantExcpetion(){
        super("Quantite de materiau en stock insuffisant");
    }
}
