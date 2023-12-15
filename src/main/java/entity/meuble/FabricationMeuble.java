package entity.meuble;

import java.util.Date;

public class FabricationMeuble {
    private int id;
    private int idMeuble;
    private Date dateFabrication;
    private int quantite;
    private double coutUnitaireFabrication;
    private double coutTotalFabrication;
    private double margeBeneficiaire;
    private double prixUnitaireDeVente;

    public FabricationMeuble() {
    }

    public FabricationMeuble(int id, int idMeuble, Date dateFabrication, int quantite,
                             double coutUnitaireFabrication, double coutTotalFabrication,
                             double margeBeneficiaire, double prixUnitaireDeVente) {
        setId(id);
        setIdMeuble(idMeuble);
        setDateFabrication(dateFabrication);
        setQuantite(quantite);
        setCoutUnitaireFabrication(coutUnitaireFabrication);
        setCoutTotalFabrication(coutTotalFabrication);
        setMargeBeneficiaire(margeBeneficiaire);
        setPrixUnitaireDeVente(prixUnitaireDeVente);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMeuble() {
        return idMeuble;
    }

    public void setIdMeuble(int idMeuble) {
        this.idMeuble = idMeuble;
    }

    public Date getDateFabrication() {
        return dateFabrication;
    }

    public void setDateFabrication(Date dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getCoutUnitaireFabrication() {
        return coutUnitaireFabrication;
    }

    public void setCoutUnitaireFabrication(double coutUnitaireFabrication) {
        this.coutUnitaireFabrication = coutUnitaireFabrication;
    }

    public double getCoutTotalFabrication() {
        return coutTotalFabrication;
    }

    public void setCoutTotalFabrication(double coutTotalFabrication) {
        this.coutTotalFabrication = coutTotalFabrication;
    }

    public double getMargeBeneficiaire() {
        return margeBeneficiaire;
    }

    public void setMargeBeneficiaire(double margeBeneficiaire) {
        this.margeBeneficiaire = margeBeneficiaire;
    }

    public double getPrixUnitaireDeVente() {
        return prixUnitaireDeVente;
    }

    public void setPrixUnitaireDeVente(double prixUnitaireDeVente) {
        this.prixUnitaireDeVente = prixUnitaireDeVente;
    }
}
