package entity.employe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VSalaireEmploye {
    Integer id;
    Integer idEmploye;
    String nomEmploye;
    LocalDateTime dateDebut;
    LocalDateTime dateFin;
    Double valeur;

    public VSalaireEmploye(Integer id, Integer idEmploye, String nomEmploye, LocalDateTime dateDebut,
            LocalDateTime dateFin,
            Double valeur) {
        setId(id);
        setIdEmploye(idEmploye);
        setNomEmploye(nomEmploye);
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

    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }

    public String getNomEmploye() {
        return nomEmploye;
    }

    public static VSalaireEmploye selectByIdEmploye(Connection connection, Integer idEmploye, LocalDateTime date)
            throws Exception {
        VSalaireEmploye vSalaireEmploye = null;
        String query = "SELECT * FROM v_salaire_employe WHERE id_employe = ? AND date_debut <= ? AND date_fin > ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idEmploye);
        statement.setObject(2, date);
        statement.setObject(3, date);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String nomEmploye = resultSet.getString("nom_employe");
            LocalDateTime dateDebut = resultSet.getTimestamp("date_debut").toLocalDateTime();
            LocalDateTime dateFin = resultSet.getTimestamp("date_fin").toLocalDateTime();
            Double valeur = resultSet.getDouble("valeur");
            vSalaireEmploye = new VSalaireEmploye(id, idEmploye, nomEmploye, dateDebut, dateFin, valeur);
        }
        statement.close();
        resultSet.close();
        return vSalaireEmploye;
    }
    
    public static List<VSalaireEmploye> selectByDate(Connection connection, LocalDateTime date)
            throws Exception {
        List<VSalaireEmploye> vSalaireEmployes = new ArrayList<>();
        String query = "SELECT * FROM v_salaire_employe WHERE date_debut <= ? AND date_fin > ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setObject(1, date);
        statement.setObject(2, date);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            Integer idEmploye = resultSet.getInt("id_employe");
            String nomEmploye = resultSet.getString("nom_employe");
            LocalDateTime dateDebut = resultSet.getTimestamp("date_debut").toLocalDateTime();
            LocalDateTime dateFin = resultSet.getTimestamp("date_fin").toLocalDateTime();
            Double valeur = resultSet.getDouble("valeur");
            VSalaireEmploye vSalaireEmploye = new VSalaireEmploye(id, idEmploye, nomEmploye, dateDebut, dateFin, valeur);
            vSalaireEmployes.add(vSalaireEmploye);
        }
        statement.close();
        resultSet.close();
        return vSalaireEmployes;
    }
}
