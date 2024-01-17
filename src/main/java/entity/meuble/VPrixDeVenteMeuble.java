package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VPrixDeVenteMeuble {
    Integer id;
    LocalDateTime dateDebut;
    LocalDateTime dateFin;
    Double valeur;
    Integer idMeuble;
    Integer idTailleMeuble;
    String nomMeuble;
    String nomTailleMeuble;
    Integer idFormuleMeuble;
    public VPrixDeVenteMeuble(Integer id, LocalDateTime dateDebut, LocalDateTime dateFin, Double valeur,
            Integer idMeuble, Integer idTailleMeuble, String nomMeuble, String nomTailleMeuble,Integer idFormuleMeuble) {
        setId(id);
        setDateDebut(dateDebut);
        setDateFin(dateFin);
        setValeur(valeur);
        setIdMeuble(idMeuble);
        setIdTailleMeuble(idTailleMeuble);
        setNomMeuble(nomMeuble);
        setNomTailleMeuble(nomTailleMeuble);
        setIdFormuleMeuble(idFormuleMeuble);
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
    public Integer getIdMeuble() {
        return idMeuble;
    }
    public void setIdMeuble(Integer idMeuble) {
        this.idMeuble = idMeuble;
    }
    public Integer getIdTailleMeuble() {
        return idTailleMeuble;
    }
    public void setIdTailleMeuble(Integer idTailleMeuble) {
        this.idTailleMeuble = idTailleMeuble;
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
    public void setIdFormuleMeuble(Integer idFormuleMeuble) {
        this.idFormuleMeuble = idFormuleMeuble;
    }

    public Integer getIdFormuleMeuble() {
        return idFormuleMeuble;
    }

    public static List<VPrixDeVenteMeuble> selectWhereDateInRange(Connection connection, LocalDateTime date) throws SQLException {
        List<VPrixDeVenteMeuble> prixList = new ArrayList<>();
        String query = "SELECT * FROM v_prix_de_vente_meuble WHERE date_debut <= ? AND date_fin > ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, date);
            statement.setObject(2, date);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    LocalDateTime dateDebut = resultSet.getTimestamp("date_debut").toLocalDateTime();
                    LocalDateTime dateFin = resultSet.getTimestamp("date_fin").toLocalDateTime();
                    Double valeur = resultSet.getDouble("valeur");
                    Integer idMeuble = resultSet.getInt("id_meuble");
                    Integer idTailleMeuble = resultSet.getInt("id_taille_meuble");
                    String nomMeuble = resultSet.getString("nom_meuble");
                    String nomTailleMeuble = resultSet.getString("nom_taille_meuble");
                    Integer idFormuleMeuble = resultSet.getInt("id_formule_meuble");
                    VPrixDeVenteMeuble prix = new VPrixDeVenteMeuble(id, dateDebut, dateFin, valeur, idMeuble,
                            idTailleMeuble, nomMeuble, nomTailleMeuble,idFormuleMeuble);
                    prixList.add(prix);
                }
            }
        }
        return prixList;
    }
    
}
