package exception;

public class FieldEmptyException extends Exception{
    public FieldEmptyException(String field) {
        super(field + " ne doit pas etre vide");
    }       
}
