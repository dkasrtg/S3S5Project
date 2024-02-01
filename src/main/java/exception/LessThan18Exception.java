package exception;

public class LessThan18Exception extends Exception{
    public LessThan18Exception() {
        super("Moins de 18 ans peut pas etre employe");
    }       
}
