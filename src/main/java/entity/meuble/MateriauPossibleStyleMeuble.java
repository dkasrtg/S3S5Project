package entity.meuble;

public class MateriauPossibleStyleMeuble {
    private int id;
    private int idStyleMeuble;
    private int idMateriau;

    public MateriauPossibleStyleMeuble() {
    }

    public MateriauPossibleStyleMeuble(int id, int idStyleMeuble, int idMateriau) {
        setId(id);
        setIdStyleMeuble(idStyleMeuble);
        setIdMateriau(idMateriau);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdStyleMeuble() {
        return idStyleMeuble;
    }

    public void setIdStyleMeuble(int idStyleMeuble) {
        this.idStyleMeuble = idStyleMeuble;
    }

    public int getIdMateriau() {
        return idMateriau;
    }

    public void setIdMateriau(int idMateriau) {
        this.idMateriau = idMateriau;
    }
}
