package entity.meuble;

public class TypePossibleMeuble {
    private int id;
    private int idMeuble;
    private int idTypeMeuble;

    public TypePossibleMeuble() {
    }

    public TypePossibleMeuble(int id, int idMeuble, int idTypeMeuble) {
        setId(id);
        setIdMeuble(idMeuble);
        setIdTypeMeuble(idTypeMeuble);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMeuble() {
        return idMeuble;
    }

    public void setIdMeuble(int idMeuble) {
        this.idMeuble = idMeuble;
    }

    public int getIdTypeMeuble() {
        return idTypeMeuble;
    }

    public void setIdTypeMeuble(int idTypeMeuble) {
        this.idTypeMeuble = idTypeMeuble;
    }
}
