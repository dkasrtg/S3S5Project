package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class FabricationMeuble {
    private Integer id;
    private Integer idMeuble;
    private LocalDate dateFabrication;
    private Double quantite;
    private Double coutUnitaireFabrication;
    private Double coutTotalFabrication;
    private Double margeBeneficiaire;
    private Double prixUnitaireDeVente;

    public FabricationMeuble() {
    }

    public FabricationMeuble(Integer idMeuble, LocalDate dateFabrication, Double quantite, Double margeBeneficiaire) {
        setIdMeuble(idMeuble);
        setDateFabrication(dateFabrication);
        setQuantite(quantite);
        setMargeBeneficiaire(margeBeneficiaire);
    }

    public FabricationMeuble(Integer id, Integer idMeuble, LocalDate dateFabrication, Double quantite,
            Double coutUnitaireFabrication, Double coutTotalFabrication,
            Double margeBeneficiaire, Double prixUnitaireDeVente) {
        setId(id);
        setIdMeuble(idMeuble);
        setDateFabrication(dateFabrication);
        setQuantite(quantite);
        setCoutUnitaireFabrication(coutUnitaireFabrication);
        setCoutTotalFabrication(coutTotalFabrication);
        setMargeBeneficiaire(margeBeneficiaire);
        setPrixUnitaireDeVente(prixUnitaireDeVente);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdMeuble() {
        return idMeuble;
    }

    public void setIdMeuble(Integer idMeuble) {
        this.idMeuble = idMeuble;
    }

    public LocalDate getDateFabrication() {
        return dateFabrication;
    }

    public void setDateFabrication(LocalDate dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getCoutUnitaireFabrication() {
        return coutUnitaireFabrication;
    }

    public void setCoutUnitaireFabrication(Double coutUnitaireFabrication) {
        this.coutUnitaireFabrication = coutUnitaireFabrication;
    }

    public Double getCoutTotalFabrication() {
        return coutTotalFabrication;
    }

    public void setCoutTotalFabrication(Double coutTotalFabrication) {
        this.coutTotalFabrication = coutTotalFabrication;
    }

    public Double getMargeBeneficiaire() {
        return margeBeneficiaire;
    }

    public void setMargeBeneficiaire(Double margeBeneficiaire) {
        this.margeBeneficiaire = margeBeneficiaire;
    }

    public Double getPrixUnitaireDeVente() {
        return prixUnitaireDeVente;
    }

    public void setPrixUnitaireDeVente(Double prixUnitaireDeVente) {
        this.prixUnitaireDeVente = prixUnitaireDeVente;
    }

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO fabrication_meuble (id_meuble, date_fabrication, quantite, marge_beneficiaire) " +
                "VALUES (?, ?, ?, ?) RETURNING id";
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, getIdMeuble());
        statement.setObject(2, getDateFabrication());
        statement.setDouble(3, getQuantite());
        statement.setDouble(4, getMargeBeneficiaire());
        statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()) {
           setId( rs.getInt(1));;
        }
        rs.close();
        statement.close();
    }

    public void update(Connection connection) throws SQLException {
        String query = "UPDATE fabrication_meuble " +
                "SET cout_unitaire_fabrication = ?, " +
                "cout_total_fabrication = ?, " +
                "prix_unitaire_vente = ? " +
                "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setDouble(1, getCoutUnitaireFabrication());
        statement.setDouble(2, getCoutTotalFabrication());
        statement.setDouble(3, getPrixUnitaireDeVente());
        statement.setInt(4, getId());
        statement.executeUpdate();
        statement.close();
    }
    
}