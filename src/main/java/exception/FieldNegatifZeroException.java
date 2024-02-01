package exception;

public class FieldNegatifZeroException extends Exception{
    public FieldNegatifZeroException(String field) {
        super(field + " ne doit pas etre negative ou nulle");
    }       
}
