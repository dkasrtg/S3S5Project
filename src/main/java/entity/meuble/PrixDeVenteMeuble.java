package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;

public class PrixDeVenteMeuble {
    Integer id;
    Integer idFormuleMeuble;
    LocalDateTime dateDebut;
    LocalDateTime dateFin;
    Double valeur;

    public PrixDeVenteMeuble(Integer id, Integer idFormuleMeuble, LocalDateTime dateDebut, LocalDateTime dateFin,
            Double valeur) {
        setId(id);
        setIdFormuleMeuble(idFormuleMeuble);
        setDateDebut(dateDebut);
        setDateFin(dateFin);
        setValeur(valeur);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdFormuleMeuble() {
        return idFormuleMeuble;
    }

    public void setIdFormuleMeuble(Integer idFormuleMeuble) {
        this.idFormuleMeuble = idFormuleMeuble;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public void insert(Connection connection) throws Exception {
        String query = "INSERT INTO prix_de_vente_meuble (id_formule_meuble, date_debut, date_fin, valeur) " +
                "VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, getIdFormuleMeuble());
        statement.setObject(2, getDateDebut());
        statement.setObject(3, getDateFin());
        statement.setDouble(4, getValeur());
        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            setId(generatedKeys.getInt(1));
        }
        statement.close();
        generatedKeys.close();
    }

    public static PrixDeVenteMeuble selectByIdFormuleMeuble(Connection connection, Integer idFormuleMeuble,
            LocalDateTime date) throws Exception {
        PrixDeVenteMeuble prix = null;
        String query = "SELECT * FROM prix_de_vente_meuble WHERE id_formule_meuble = ? AND date_debut <= ? AND date_fin > ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idFormuleMeuble);
        statement.setObject(2, date);
        statement.setObject(3, date);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            LocalDateTime dateDebut = resultSet.getTimestamp("date_debut").toLocalDateTime();
            LocalDateTime dateFin = resultSet.getTimestamp("date_fin").toLocalDateTime();
            Double valeur = resultSet.getDouble("valeur");

            prix = new PrixDeVenteMeuble(id, idFormuleMeuble, dateDebut, dateFin, valeur);
        }
        statement.close();
        resultSet.close();
        return prix;
    }

    public static PrixDeVenteMeuble selectByIdFormuleMeubleAndDateFin(Connection connection, Integer idFormuleMeuble,
            LocalDateTime dateFin) throws Exception {
        PrixDeVenteMeuble prix = null;
        String query = "SELECT * FROM prix_de_vente_meuble WHERE id_formule_meuble = ? AND date_fin = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idFormuleMeuble);
        statement.setObject(2, dateFin);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            LocalDateTime dateDebut = resultSet.getTimestamp("date_debut").toLocalDateTime();
            Double valeur = resultSet.getDouble("valeur");
            prix = new PrixDeVenteMeuble(id, idFormuleMeuble, dateDebut, dateFin, valeur);
        }
        statement.close();
        resultSet.close();
        return prix;
    }

    public void update(Connection connection) throws Exception {
        String query = "UPDATE prix_de_vente_meuble SET id_formule_meuble = ?, date_debut = ?, date_fin = ?, valeur = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, getIdFormuleMeuble());
        statement.setObject(2, getDateDebut());
        statement.setObject(3, getDateFin());
        statement.setDouble(4, getValeur());
        statement.setInt(5, getId());
        statement.executeUpdate();
        statement.close();
    }

}
