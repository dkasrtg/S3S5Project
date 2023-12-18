package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DetailFabricationMeuble {
    private Integer id;
    private Integer idFabricationMeuble;
    private Integer idComposantMeuble;
    private Integer idStockageMateriau;
    private Double quantite;
    private Double coutUnitaire;
    private Double coutTotal;

    public DetailFabricationMeuble() {
    }

    public DetailFabricationMeuble(Integer id, Integer idFabricationMeuble, Integer idComposantMeuble,
                                   Integer idStockageMateriau, Double quantite, Double coutUnitaire, Double coutTotal) {
        setId(id);
        setIdFabricationMeuble(idFabricationMeuble);
        setIdComposantMeuble(idComposantMeuble);
        setIdStockageMateriau(idStockageMateriau);
        setQuantite(quantite);
        setCoutUnitaire(coutUnitaire);
        setCoutTotal(coutTotal);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdFabricationMeuble() {
        return idFabricationMeuble;
    }

    public void setIdFabricationMeuble(Integer idFabricationMeuble) {
        this.idFabricationMeuble = idFabricationMeuble;
    }

    public Integer getIdComposantMeuble() {
        return idComposantMeuble;
    }

    public void setIdComposantMeuble(Integer idComposantMeuble) {
        this.idComposantMeuble = idComposantMeuble;
    }

    public Integer getIdStockageMateriau() {
        return idStockageMateriau;
    }

    public void setIdStockageMateriau(Integer idStockageMateriau) {
        this.idStockageMateriau = idStockageMateriau;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(Double coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }

    public Double getCoutTotal() {
        return coutTotal;
    }

    public void setCoutTotal(Double coutTotal) {
        this.coutTotal = coutTotal;
    }

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO detail_fabrication_meuble (id_fabrication_meuble, id_composant_meuble, " +
                "id_stockage_materiau, quantite, cout_unitaire, cout_total) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, this.idFabricationMeuble);
        statement.setInt(2, this.idComposantMeuble);
        statement.setInt(3, this.idStockageMateriau);
        statement.setDouble(4, this.quantite);
        statement.setDouble(5, this.coutUnitaire);
        statement.setDouble(6, this.coutTotal);
        statement.executeUpdate();
        statement.close();
    }
}
