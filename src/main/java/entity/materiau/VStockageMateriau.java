package entity.materiau;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VStockageMateriau {
    private Integer id;
    private Integer idMateriau;
    private Integer idDimensionMateriau;
    private Double quantiteStockage;
    private Date dateStockage;
    private Double prixUnitaire;
    private Double prixTotal;
    private String nomMateriau;
    private Integer idTypeMateriau;
    private String nomTypeMateriau;
    private String dimension;

    public VStockageMateriau() {
    }

    public VStockageMateriau(Integer id, Integer idMateriau, Integer idDimensionMateriau, Double quantiteStockage,
            Date dateStockage, Double prixUnitaire, Double prixTotal, String nomMateriau,
            Integer idTypeMateriau, String nomTypeMateriau, String dimension) {
        setId(id);
        setIdMateriau(idMateriau);
        setIdDimensionMateriau(idDimensionMateriau);
        setQuantiteStockage(quantiteStockage);
        setDateStockage(dateStockage);
        setPrixUnitaire(prixUnitaire);
        setPrixTotal(prixTotal);
        setNomMateriau(nomMateriau);
        setIdTypeMateriau(idTypeMateriau);
        setNomTypeMateriau(nomTypeMateriau);
        setDimension(dimension);
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

    public Date getDateStockage() {
        return dateStockage;
    }

    public void setDateStockage(Date dateStockage) {
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

    public String getNomMateriau() {
        return nomMateriau;
    }

    public void setNomMateriau(String nomMateriau) {
        this.nomMateriau = nomMateriau;
    }

    public Integer getIdTypeMateriau() {
        return idTypeMateriau;
    }

    public void setIdTypeMateriau(Integer idTypeMateriau) {
        this.idTypeMateriau = idTypeMateriau;
    }

    public String getNomTypeMateriau() {
        return nomTypeMateriau;
    }

    public void setNomTypeMateriau(String nomTypeMateriau) {
        this.nomTypeMateriau = nomTypeMateriau;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public static List<VStockageMateriau> list(Connection connection) throws SQLException {
        List<VStockageMateriau> stockageMateriauList = new ArrayList<>();
        String query = "SELECT * FROM v_stockage_materiau";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            Integer idMateriau = resultSet.getInt("id_materiau");
            Integer idDimensionMateriau = resultSet.getInt("id_dimension_materiau");
            double quantiteStockage = resultSet.getDouble("quantite_stockage");
            Date dateStockage = resultSet.getDate("date_stockage");
            double prixUnitaire = resultSet.getDouble("prix_unitaire");
            double prixTotal = resultSet.getDouble("prix_total");
            String nomMateriau = resultSet.getString("nom_materiau");
            Integer idTypeMateriau = resultSet.getInt("id_type_materiau");
            String nomTypeMateriau = resultSet.getString("nom_type_materiau");
            String dimension = resultSet.getString("dimension");

            VStockageMateriau stockageMateriau = new VStockageMateriau(
                    id, idMateriau, idDimensionMateriau, quantiteStockage, dateStockage, prixUnitaire,
                    prixTotal, nomMateriau, idTypeMateriau, nomTypeMateriau, dimension);

            stockageMateriauList.add(stockageMateriau);
        }

        statement.close();
        resultSet.close();

        return stockageMateriauList;
    }
}
