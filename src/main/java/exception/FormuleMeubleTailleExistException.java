package exception;

public class FormuleMeubleTailleExistException extends Exception{
    public FormuleMeubleTailleExistException() {
        super("Ce meuble a deja une formule pour cette taille");
    }
}