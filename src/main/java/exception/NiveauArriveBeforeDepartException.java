package exception;

public class NiveauArriveBeforeDepartException extends Exception{
    public NiveauArriveBeforeDepartException(){
        super("Le niveau arrive doit etre apres le depart");
    }
}
