package entity.materiau;

public class Materiau {
    private int id;
    private String nom;
    private int idTypeMateriau;
    private String description;

    public Materiau() {
    }

    public Materiau(int id, String nom, int idTypeMateriau, String description) {
        setId(id);
        setNom(nom);
        setIdTypeMateriau(idTypeMateriau);
        setDescription(description);
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

    public int getIdTypeMateriau() {
        return idTypeMateriau;
    }

    public void setIdTypeMateriau(int idTypeMateriau) {
        this.idTypeMateriau = idTypeMateriau;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
