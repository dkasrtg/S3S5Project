package entity.meuble;

public class Meuble {
    private int id;
    private String nom;
    private int idStyleMeuble;
    private int idCategorieMeuble;
    private String description;

    public Meuble() {
    }

    public Meuble(int id, String nom, int idStyleMeuble, int idCategorieMeuble, String description) {
        setId(id);
        setNom(nom);
        setIdStyleMeuble(idStyleMeuble);
        setIdCategorieMeuble(idCategorieMeuble);
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

    public int getIdStyleMeuble() {
        return idStyleMeuble;
    }

    public void setIdStyleMeuble(int idStyleMeuble) {
        this.idStyleMeuble = idStyleMeuble;
    }

    public int getIdCategorieMeuble() {
        return idCategorieMeuble;
    }

    public void setIdCategorieMeuble(int idCategorieMeuble) {
        this.idCategorieMeuble = idCategorieMeuble;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
