package entity.employe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

public class UtilisationEmploye {
    private Integer id;
    private Integer idMouvementMeuble;
    private LocalDateTime dateUtilisation;
    private Integer idEmploye;
    private Integer nombre;
    private Double dureeUtilisation;
    private Double salaireUnitaire;
    private Double salaireTotal;
    private String description;

    public UtilisationEmploye(Integer id, Integer idMouvementMeuble, LocalDateTime dateUtilisation, Integer idEmploye,
            Integer nombre, Double dureeUtilisation, Double salaireUnitaire, Double salaireTotal,
            String description) {
        setId(id);
        setIdMouvementMeuble(idMouvementMeuble);
        setDateUtilisation(dateUtilisation);
        setIdEmploye(idEmploye);
        setNombre(nombre);
        setDureeUtilisation(dureeUtilisation);
        setSalaireUnitaire(salaireUnitaire);
        setSalaireTotal(salaireTotal);
        setDescription(description);
    }

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

    public void insert(Connection connection) throws Exception {
        String query = "INSERT INTO utilisation_employe (id_mouvement_meuble, date_utilisation, id_employe, " +
                "nombre, duree_utilisation, salaire_unitaire, salaire_total, description) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, getIdMouvementMeuble());
        statement.setObject(2, getDateUtilisation());
        statement.setInt(3, getIdEmploye());
        statement.setInt(4, getNombre());
        statement.setDouble(5, getDureeUtilisation());
        statement.setDouble(6, getSalaireUnitaire());
        statement.setDouble(7, getSalaireTotal());
        statement.setString(8, getDescription());
        statement.executeUpdate();
        statement.close();
    }
}
