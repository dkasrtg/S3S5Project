package entity.employe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;

public class SalaireEmploye {
    Integer id;
    Integer idEmploye;
    LocalDateTime dateDebut;
    LocalDateTime dateFin;
    Double valeur;

    public SalaireEmploye(Integer id, Integer idEmploye, LocalDateTime dateDebut, LocalDateTime dateFin,
            Double valeur) {
        setId(id);
        setIdEmploye(idEmploye);
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

    public Integer getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(Integer idEmploye) {
        this.idEmploye = idEmploye;
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
        String query = "INSERT INTO salaire_employe (id_employe, date_debut, date_fin, valeur) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, getIdEmploye());
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

    public static SalaireEmploye selectByIdEmployeWhereDateFin(Connection connection, Integer idEmploye,
            LocalDateTime dateFin) throws Exception {
        String query = "SELECT * FROM salaire_employe WHERE id_employe = ? AND date_fin = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idEmploye);
        statement.setObject(2, dateFin);
        ResultSet resultSet = statement.executeQuery();
        SalaireEmploye salaireEmploye = null;
        if (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            LocalDateTime dateDebut = resultSet.getTimestamp("date_debut").toLocalDateTime();
            Double valeur = resultSet.getDouble("valeur");
            salaireEmploye =  new SalaireEmploye(id, idEmploye, dateDebut, dateFin, valeur);
        }
        statement.close();
        resultSet.close();
        return salaireEmploye;
    }

    public void update(Connection connection) throws Exception {
        String query = "UPDATE salaire_employe SET date_debut = ?, date_fin = ?, valeur = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setObject(1, getDateDebut());
        statement.setObject(2, getDateFin());
        statement.setDouble(3, getValeur());
        statement.setInt(4, getId());
        statement.executeUpdate();
        statement.close();
    }
}
