package entity.materiau;

import java.math.BigDecimal;
import java.util.Date;

public class StockageMateriau {
    private int id;
    private int idMateriau;
    private int idDimensionMateriau;
    private BigDecimal quantiteStockage;
    private Date dateStockage;
    private BigDecimal prixUnitaire;
    private BigDecimal prixTotal;

    public StockageMateriau() {
    }

    public StockageMateriau(int id, int idMateriau, int idDimensionMateriau, BigDecimal quantiteStockage,
                            Date dateStockage, BigDecimal prixUnitaire, BigDecimal prixTotal) {
        setId(id);
        setIdMateriau(idMateriau);
        setIdDimensionMateriau(idDimensionMateriau);
        setQuantiteStockage(quantiteStockage);
        setDateStockage(dateStockage);
        setPrixUnitaire(prixUnitaire);
        setPrixTotal(prixTotal);
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

    public BigDecimal getQuantiteStockage() {
        return quantiteStockage;
    }

    public void setQuantiteStockage(BigDecimal quantiteStockage) {
        this.quantiteStockage = quantiteStockage;
    }

    public Date getDateStockage() {
        return dateStockage;
    }

    public void setDateStockage(Date dateStockage) {
        this.dateStockage = dateStockage;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public BigDecimal getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
    }
}
