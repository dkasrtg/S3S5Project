package exception;

public class AtLeastOneException extends Exception{
    public AtLeastOneException(String name){
        super(name + " doit avoir au moins 1");
    }
}
