package entity.meuble;

public class QuantiteDimensionMateriauMeuble {
    private int id;
    private int idMateriau;
    private int idDimensionMateriau;
    private double quantite;
    private int idMeuble;

    public QuantiteDimensionMateriauMeuble() {
    }

    public QuantiteDimensionMateriauMeuble(int id, int idMateriau, int idDimensionMateriau, double quantite, int idMeuble) {
        setId(id);
        setIdMateriau(idMateriau);
        setIdDimensionMateriau(idDimensionMateriau);
        setQuantite(quantite);
        setIdMeuble(idMeuble);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMateriau() {
        return idMateriau;
    }

    public void setIdMateriau(int idMateriau) {
        this.idMateriau = idMateriau;
    }

    public int getIdDimensionMateriau() {
        return idDimensionMateriau;
    }

    public void setIdDimensionMateriau(int idDimensionMateriau) {
        this.idDimensionMateriau = idDimensionMateriau;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public int getIdMeuble() {
        return idMeuble;
    }

    public void setIdMeuble(int idMeuble) {
        this.idMeuble = idMeuble;
    }
}
