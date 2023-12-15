package entity.materiau;

public class TypeMateriau {
    private int id;
    private String nom;

    public TypeMateriau() {
    }

    public TypeMateriau(int id, String nom) {
        setId(id);
        setNom(nom);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
