package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class VenteMeuble {
    private Integer id;
    private LocalDateTime dateVente;
    private Integer idClient;
    private Double prixHT;
    private Double remise;
    private Double taxe;
    private Double prixTTC;

    public VenteMeuble(Integer id, LocalDateTime dateVente, Integer idClient,
            Double prixHT, Double remise, Double taxe, Double prixTTC) {
        setId(id);
        setDateVente(dateVente);
        setIdClient(idClient);
        setPrixHT(prixHT);
        setRemise(remise);
        setTaxe(taxe);
        setPrixTTC(prixTTC);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateVente() {
        return dateVente;
    }

    public void setDateVente(LocalDateTime dateVente) {
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

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO vente_meuble (date_vente, id_client, prix_ht, remise, taxe, prix_ttc) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setObject(1, getDateVente());
            statement.setInt(2, getIdClient());
            statement.setDouble(3, getPrixHT());
            statement.setDouble(4, getRemise());
            statement.setDouble(5, getTaxe());
            statement.setDouble(6, getPrixTTC());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void update(Connection connection) throws SQLException {
        String query = "UPDATE vente_meuble SET date_vente = ?, prix_ht = ?, remise = ?, taxe = ?, prix_ttc = ? " +
                "WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, getDateVente());
            statement.setDouble(2, getPrixHT());
            statement.setDouble(3, getRemise());
            statement.setDouble(4, getTaxe());
            statement.setDouble(5, getPrixTTC());
            statement.setInt(6, getId());
            statement.executeUpdate();
        }
    }

}
