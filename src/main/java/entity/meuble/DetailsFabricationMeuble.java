package entity.meuble;

public class DetailsFabricationMeuble {
    private int id;
    private int idFabricationMeuble;
    private int idQuantiteDimensionMateriauMeuble;
    private double quantite;

    public DetailsFabricationMeuble() {
    }

    public DetailsFabricationMeuble(int id, int idFabricationMeuble, int idQuantiteDimensionMateriauMeuble, double quantite) {
        setId(id);
        setIdFabricationMeuble(idFabricationMeuble);
        setIdQuantiteDimensionMateriauMeuble(idQuantiteDimensionMateriauMeuble);
        setQuantite(quantite);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFabricationMeuble() {
        return idFabricationMeuble;
    }

    public void setIdFabricationMeuble(int idFabricationMeuble) {
        this.idFabricationMeuble = idFabricationMeuble;
    }

    public int getIdQuantiteDimensionMateriauMeuble() {
        return idQuantiteDimensionMateriauMeuble;
    }

    public void setIdQuantiteDimensionMateriauMeuble(int idQuantiteDimensionMateriauMeuble) {
        this.idQuantiteDimensionMateriauMeuble = idQuantiteDimensionMateriauMeuble;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
}
