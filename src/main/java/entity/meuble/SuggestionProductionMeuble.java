package entity.meuble;

public class SuggestionProductionMeuble {
    String nomMeuble;
    String nomTailleMeuble;
    Double quantite;
    Double prixDeVenteUnitaire;
    Double prixDeVenteTotal;
    Double prixDeRevientUnitaire;
    Double prixDeRevientTotal;
    Double beneficeUnitaire;
    Double beneficeTotal;
    public SuggestionProductionMeuble(String nomMeuble, String nomTailleMeuble, Double quantite,
            Double prixDeVenteUnitaire, Double prixDeVenteTotal, Double prixDeRevientUnitaire,
            Double prixDeRevientTotal, Double beneficeUnitaire, Double beneficeTotal) {
        setNomMeuble(nomMeuble);
        setNomTailleMeuble(nomTailleMeuble);
        setQuantite(quantite);
        setPrixDeRevientUnitaire(prixDeRevientUnitaire);
        setPrixDeRevientTotal(prixDeRevientTotal);
        setPrixDeVenteTotal(prixDeVenteTotal);
        setPrixDeVenteUnitaire(prixDeVenteUnitaire);
        setBeneficeTotal(beneficeTotal);
        setBeneficeUnitaire(beneficeUnitaire);
    }
    public String getNomMeuble() {
        return nomMeuble;
    }
    public void setNomMeuble(String nomMeuble) {
        this.nomMeuble = nomMeuble;
    }
    public String getNomTailleMeuble() {
        return nomTailleMeuble;
    }
    public void setNomTailleMeuble(String nomTailleMeuble) {
        this.nomTailleMeuble = nomTailleMeuble;
    }
    public Double getQuantite() {
        return quantite;
    }
    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }
    public Double getPrixDeVenteUnitaire() {
        return prixDeVenteUnitaire;
    }
    public void setPrixDeVenteUnitaire(Double prixDeVenteUnitaire) {
        this.prixDeVenteUnitaire = prixDeVenteUnitaire;
    }
    public Double getPrixDeVenteTotal() {
        return prixDeVenteTotal;
    }
    public void setPrixDeVenteTotal(Double prixDeVenteTotal) {
        this.prixDeVenteTotal = prixDeVenteTotal;
    }
    public Double getPrixDeRevientUnitaire() {
        return prixDeRevientUnitaire;
    }
    public void setPrixDeRevientUnitaire(Double prixDeRevietnUnitaire) {
        this.prixDeRevientUnitaire = prixDeRevietnUnitaire;
    }
    public Double getPrixDeRevientTotal() {
        return prixDeRevientTotal;
    }
    public void setPrixDeRevientTotal(Double prixDeRevientTotal) {
        this.prixDeRevientTotal = prixDeRevientTotal;
    }
    public Double getBeneficeUnitaire() {
        return beneficeUnitaire;
    }
    public void setBeneficeUnitaire(Double beneficeUnitaire) {
        this.beneficeUnitaire = beneficeUnitaire;
    }
    public Double getBeneficeTotal() {
        return beneficeTotal;
    }
    public void setBeneficeTotal(Double beneficeTotal) {
        this.beneficeTotal = beneficeTotal;
    }
    
    
}
