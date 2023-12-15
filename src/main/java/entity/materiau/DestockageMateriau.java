package entity.materiau;

import java.math.BigDecimal;
import java.util.Date;

public class DestockageMateriau {
    private int id;
    private int idDetailsFabricationMeuble;
    private int idStockageMateriau;
    private BigDecimal quantite;
    private Date dateDestockage;

    public DestockageMateriau() {
    }

    public DestockageMateriau(int id, int idDetailsFabricationMeuble, int idStockageMateriau,
                              BigDecimal quantite, Date dateDestockage) {
        setId(id);
        setIdDetailsFabricationMeuble(idDetailsFabricationMeuble);
        setIdStockageMateriau(idStockageMateriau);
        setQuantite(quantite);
        setDateDestockage(dateDestockage);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDetailsFabricationMeuble() {
        return idDetailsFabricationMeuble;
    }

    public void setIdDetailsFabricationMeuble(int idDetailsFabricationMeuble) {
        this.idDetailsFabricationMeuble = idDetailsFabricationMeuble;
    }

    public int getIdStockageMateriau() {
        return idStockageMateriau;
    }

    public void setIdStockageMateriau(int idStockageMateriau) {
        this.idStockageMateriau = idStockageMateriau;
    }

    public BigDecimal getQuantite() {
        return quantite;
    }

    public void setQuantite(BigDecimal quantite) {
        this.quantite = quantite;
    }

    public Date getDateDestockage() {
        return dateDestockage;
    }

    public void setDateDestockage(Date dateDestockage) {
        this.dateDestockage = dateDestockage;
    }
}
