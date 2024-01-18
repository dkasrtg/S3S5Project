package entity.employe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VUtilisationEmploye {
    private Integer id;
    private Integer idMouvementMeuble;
    private LocalDateTime dateUtilisation;
    private Integer idEmploye;
    private Integer nombre;
    private Double dureeUtilisation;
    private Double salaireUnitaire;
    private Double salaireTotal;
    private String description;
    private String nomEmploye;

    public VUtilisationEmploye() {
    }

    public VUtilisationEmploye(Integer id, Integer idMouvementMeuble, LocalDateTime dateUtilisation,
            Integer idEmploye, Integer nombre, Double dureeUtilisation, Double salaireUnitaire,
            Double salaireTotal, String description, String nomEmploye) {
        setId(id);
        setIdMouvementMeuble(idMouvementMeuble);
        setDateUtilisation(dateUtilisation);
        setIdEmploye(idEmploye);
        setNombre(nombre);
        setDureeUtilisation(dureeUtilisation);
        setSalaireUnitaire(salaireUnitaire);
        setSalaireTotal(salaireTotal);
        setDescription(description);
        setNomEmploye(nomEmploye);
    }

    // Add getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdMouvementMeuble() {
        return idMouvementMeuble;
    }

    public void setIdMouvementMeuble(Integer idMouvementMeuble) {
        this.idMouvementMeuble = idMouvementMeuble;
    }

    public LocalDateTime getDateUtilisation() {
        return dateUtilisation;
    }

    public void setDateUtilisation(LocalDateTime dateUtilisation) {
        this.dateUtilisation = dateUtilisation;
    }

    public Integer getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(Integer idEmploye) {
        this.idEmploye = idEmploye;
    }

    public Integer getNombre() {
        return nombre;
    }

    public void setNombre(Integer nombre) {
        this.nombre = nombre;
    }

    public Double getDureeUtilisation() {
        return dureeUtilisation;
    }

    public void setDureeUtilisation(Double dureeUtilisation) {
        this.dureeUtilisation = dureeUtilisation;
    }

    public Double getSalaireUnitaire() {
        return salaireUnitaire;
    }

    public void setSalaireUnitaire(Double salaireUnitaire) {
        this.salaireUnitaire = salaireUnitaire;
    }

    public Double getSalaireTotal() {
        return salaireTotal;
    }

    public void setSalaireTotal(Double salaireTotal) {
        this.salaireTotal = salaireTotal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNomEmploye() {
        return nomEmploye;
    }

    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }

    public static List<VUtilisationEmploye> selectByDateRange(Connection connection,
            LocalDateTime startDate, LocalDateTime endDate) throws SQLException {
        List<VUtilisationEmploye> utilisations = new ArrayList<>();
        String query = "SELECT * FROM v_utilisation_employe WHERE date_utilisation>= ? AND date_utilisation<= ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, startDate);
            statement.setObject(2, endDate);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // Extract data and create VUtilisationEmploye objects
                    Integer id = resultSet.getInt("id");
                    Integer idMouvementMeuble = resultSet.getInt("id_mouvement_meuble");
                    LocalDateTime dateUtilisation = resultSet.getTimestamp("date_utilisation").toLocalDateTime();
                    Integer idEmploye = resultSet.getInt("id_employe");
                    Integer nombre = resultSet.getInt("nombre");
                    Double dureeUtilisation = resultSet.getDouble("duree_utilisation");
                    Double salaireUnitaire = resultSet.getDouble("salaire_unitaire");
                    Double salaireTotal = resultSet.getDouble("salaire_total");
                    String description = resultSet.getString("description");
                    String nomEmploye = resultSet.getString("nom_employe");

                    VUtilisationEmploye utilisation = new VUtilisationEmploye(
                            id, idMouvementMeuble, dateUtilisation, idEmploye, nombre, dureeUtilisation,
                            salaireUnitaire, salaireTotal, description, nomEmploye);
                    utilisations.add(utilisation);
                }
            }
        }
        return utilisations;
    }
}
