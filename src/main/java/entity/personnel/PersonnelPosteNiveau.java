package entity.personnel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class PersonnelPosteNiveau {
    private Integer id;
    private Integer idPersonnel;
    private Integer idPoste;
    private Integer idNiveau;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    public PersonnelPosteNiveau(Integer id, Integer idPersonnel, Integer idPoste, Integer idNiveau, LocalDateTime dateDebut,
            LocalDateTime dateFin) {
        this.id = id;
        this.idPersonnel = idPersonnel;
        this.idPoste = idPoste;
        this.idNiveau = idNiveau;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(Integer idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public Integer getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(Integer idPoste) {
        this.idPoste = idPoste;
    }

    public Integer getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(Integer idNiveau) {
        this.idNiveau = idNiveau;
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

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO personnel_poste_niveau (id_personnel, id_poste, id_niveau, date_debut, date_fin) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, getIdPersonnel());
            statement.setInt(2, getIdPoste());
            statement.setInt(3, getIdNiveau());
            statement.setObject(4, getDateDebut());
            statement.setObject(5, getDateFin());

            statement.executeUpdate();

            // Get the generated key (id) and set it
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    setId(generatedKeys.getInt(1));
                }
            }
        }
    }
}
