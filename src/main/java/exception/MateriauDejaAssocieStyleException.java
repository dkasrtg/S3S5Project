package exception;

public class MateriauDejaAssocieStyleException extends Exception{
    public MateriauDejaAssocieStyleException(){
        super("Ce materiau et ce style sont deja associe");
    }
}
