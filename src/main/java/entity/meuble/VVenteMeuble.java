package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VVenteMeuble {
    private Integer id;
    private String dateVente;
    private Integer idClient;
    private Double prixHT;
    private Double remise;
    private Double taxe;
    private Double prixTTC;
    private String nomClient;
    private String prenomClient;
    private String telephoneClient;

    public VVenteMeuble(Integer id, String dateVente, Integer idClient, Double prixHT, Double remise,
            Double taxe, Double prixTTC, String nomClient, String prenomClient, String telephoneClient) {
        setId(id);
        setDateVente(dateVente);
        setIdClient(idClient);
        setPrixHT(prixHT);
        setRemise(remise);
        setTaxe(taxe);
        setPrixTTC(prixTTC);
        setNomClient(nomClient);
        setPrenomClient(prenomClient);
        setTelephoneClient(telephoneClient);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateVente() {
        return dateVente;
    }

    public void setDateVente(String dateVente) {
        this.dateVente = dateVente;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public Double getPrixHT() {
        return prixHT;
    }

    public void setPrixHT(Double prixHT) {
        this.prixHT = prixHT;
    }

    public Double getRemise() {
        return remise;
    }

    public void setRemise(Double remise) {
        this.remise = remise;
    }

    public Double getTaxe() {
        return taxe;
    }

    public void setTaxe(Double taxe) {
        this.taxe = taxe;
    }

    public Double getPrixTTC() {
        return prixTTC;
    }

    public void setPrixTTC(Double prixTTC) {
        this.prixTTC = prixTTC;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public String getTelephoneClient() {
        return telephoneClient;
    }

    public void setTelephoneClient(String telephoneClient) {
        this.telephoneClient = telephoneClient;
    }

    public static List<VVenteMeuble> list(Connection connection, LocalDateTime dateDebut, LocalDateTime dateFin)
            throws Exception {
        List<VVenteMeuble> ventes = new ArrayList<>();
        String query = "SELECT * FROM v_vente_meuble where date_vente>= ? and date_vente<= ? order by date_vente asc";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setObject(1, dateDebut);
        statement.setObject(2, dateFin);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String dateVente = resultSet.getString("date_vente");
            Integer idClient = resultSet.getInt("id_client");
            Double prixHT = resultSet.getDouble("prix_ht");
            Double remise = resultSet.getDouble("remise");
            Double taxe = resultSet.getDouble("taxe");
            Double prixTTC = resultSet.getDouble("prix_ttc");
            String nomClient = resultSet.getString("nom_client");
            String prenomClient = resultSet.getString("prenom_client");
            String telephoneClient = resultSet.getString("telephone_client");
            VVenteMeuble vente = new VVenteMeuble(id, dateVente, idClient, prixHT, remise, taxe, prixTTC,
                    nomClient, prenomClient, telephoneClient);
            ventes.add(vente);
        }
        return ventes;
    }

    public static VVenteMeuble selectById(Connection connection, Integer id) throws Exception {
        String query = "SELECT * FROM v_vente_meuble WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        VVenteMeuble vVenteMeuble = null;
        if (resultSet.next()) {
            String dateVente = resultSet.getString("date_vente");
            Integer idClient = resultSet.getInt("id_client");
            Double prixHT = resultSet.getDouble("prix_ht");
            Double remise = resultSet.getDouble("remise");
            Double taxe = resultSet.getDouble("taxe");
            Double prixTTC = resultSet.getDouble("prix_ttc");
            String nomClient = resultSet.getString("nom_client");
            String prenomClient = resultSet.getString("prenom_client");
            String telephoneClient = resultSet.getString("telephone_client");
            vVenteMeuble = new VVenteMeuble(id, dateVente, idClient, prixHT, remise, taxe, prixTTC,
                    nomClient, prenomClient, telephoneClient);
        }
        statement.close();
        resultSet.close();
        return vVenteMeuble;
    }
}
