package entity.materiau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import exception.DateAfterNowException;

public class StockageMateriau {
    private Integer id;
    private Integer idMateriau;
    private Integer idDimensionMateriau;
    private Integer idUniteMateriau;
    private Double quantiteStockage;
    private LocalDate dateStockage;
    private Double prixUnitaire;
    private Double prixTotal;

    public StockageMateriau() {
    }

    public StockageMateriau(Integer id, Integer idMateriau, Integer idDimensionMateriau,Integer idUniteMateriau, Double quantiteStockage,
            LocalDate dateStockage, Double prixUnitaire, Double prixTotal) throws Exception{
        setId(id);
        setIdMateriau(idMateriau);
        setIdDimensionMateriau(idDimensionMateriau);
        setIdUniteMateriau(idUniteMateriau);
        setQuantiteStockage(quantiteStockage);
        setDateStockage(dateStockage);
        setPrixUnitaire(prixUnitaire);
        setPrixTotal(prixTotal);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdMateriau() {
        return idMateriau;
    }

    public void setIdMateriau(Integer idMateriau) {
        this.idMateriau = idMateriau;
    }

    public Integer getIdDimensionMateriau() {
        return idDimensionMateriau;
    }

    public void setIdDimensionMateriau(Integer idDimensionMateriau) {
        this.idDimensionMateriau = idDimensionMateriau;
    }

    public Double getQuantiteStockage() {
        return quantiteStockage;
    }

    public void setQuantiteStockage(Double quantiteStockage) {
        this.quantiteStockage = quantiteStockage;
    }

    public LocalDate getDateStockage() {
        return dateStockage;
    }

    public void setDateStockage(LocalDate dateStockage) throws Exception{
        if (dateStockage.isAfter(LocalDate.now())) {
            throw new DateAfterNowException();
        }
        this.dateStockage = dateStockage;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Integer getIdUniteMateriau() {
        return idUniteMateriau;
    }

    public void setIdUniteMateriau(Integer idUniteMateriau) {
        this.idUniteMateriau = idUniteMateriau;
    }

    public void insert(Connection connection) throws Exception {
        String query = "INSERT INTO stockage_materiau (id_materiau, id_dimension_materiau, quantite_stockage, date_stockage, prix_unitaire, prix_total, id_unite_materiau) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, getIdMateriau());
        statement.setInt(2, getIdDimensionMateriau());
        statement.setDouble(3, getQuantiteStockage());
        statement.setObject(4, getDateStockage());
        statement.setDouble(5, getPrixUnitaire());
        statement.setDouble(6, getPrixTotal());
        statement.setInt(7, getIdUniteMateriau());
        statement.executeUpdate();
    }
}
