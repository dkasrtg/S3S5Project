package entity.personnel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TauxHorairePoste {
    private Integer id;
    private Integer idPoste;
    private Timestamp dateDebut;
    private Timestamp dateFin;
    private Double valeur;

    
    public TauxHorairePoste() {
    }

    public TauxHorairePoste(Integer id, Integer idPoste, Timestamp dateDebut, Timestamp dateFin, Double valeur) {
        this.id = id;
        this.idPoste = idPoste;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.valeur = valeur;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(Integer idPoste) {
        this.idPoste = idPoste;
    }

    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Timestamp getDateFin() {
        return dateFin;
    }

    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public static TauxHorairePoste selectByIdPoste(Connection connection, Integer idPoste) throws SQLException {
        String query = "SELECT * FROM taux_horaire_poste WHERE id_poste = ? LIMIT 1";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idPoste);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    TauxHorairePoste tauxHorairePoste = new TauxHorairePoste();
                    tauxHorairePoste.setId(resultSet.getInt("id"));
                    tauxHorairePoste.setIdPoste(resultSet.getInt("id_poste"));
                    tauxHorairePoste.setDateDebut(resultSet.getTimestamp("date_debut"));
                    tauxHorairePoste.setDateFin(resultSet.getTimestamp("date_fin"));
                    tauxHorairePoste.setValeur(resultSet.getDouble("valeur"));
                    return tauxHorairePoste;
                }
            }
        }

        return null;
    }
}
