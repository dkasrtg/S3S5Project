package exception;

public class FormuleMeubleTailleNotExistException extends Exception{
    public FormuleMeubleTailleNotExistException() {
        super("Ce meuble n existe pas en cette taille");
    }
}