package entity.personnel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VTauxHorairePersonnel {

    private int id;
    private int idPersonnel;
    private int idPoste;
    private int idNiveau;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String nomPersonnel;
    private String prenomPersonnel;
    private String nomPoste;
    private String nomNiveau;
    private double valeur;
    private double multi;
    private double tauxHoraire;

    public VTauxHorairePersonnel() {
    }

    public VTauxHorairePersonnel(int id, int idPersonnel, int idPoste, int idNiveau, LocalDateTime dateDebut,
            LocalDateTime dateFin, String nomPersonnel, String prenomPersonnel, String nomPoste, String nomNiveau,
            double valeur, double multi, double tauxHoraire) {
        this.id = id;
        this.idPersonnel = idPersonnel;
        this.idPoste = idPoste;
        this.idNiveau = idNiveau;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nomPersonnel = nomPersonnel;
        this.prenomPersonnel = prenomPersonnel;
        this.nomPoste = nomPoste;
        this.nomNiveau = nomNiveau;
        this.valeur = valeur;
        this.multi = multi;
        this.tauxHoraire = tauxHoraire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(int idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    public int getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(int idPoste) {
        this.idPoste = idPoste;
    }

    public int getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(int idNiveau) {
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

    public String getNomPersonnel() {
        return nomPersonnel;
    }

    public void setNomPersonnel(String nomPersonnel) {
        this.nomPersonnel = nomPersonnel;
    }

    public String getPrenomPersonnel() {
        return prenomPersonnel;
    }

    public void setPrenomPersonnel(String prenomPersonnel) {
        this.prenomPersonnel = prenomPersonnel;
    }

    public String getNomPoste() {
        return nomPoste;
    }

    public void setNomPoste(String nomPoste) {
        this.nomPoste = nomPoste;
    }

    public String getNomNiveau() {
        return nomNiveau;
    }

    public void setNomNiveau(String nomNiveau) {
        this.nomNiveau = nomNiveau;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public double getMulti() {
        return multi;
    }

    public void setMulti(double multi) {
        this.multi = multi;
    }

    public double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public static List<VTauxHorairePersonnel> selectByDate(Connection connection, LocalDateTime date)
            throws SQLException {
        List<VTauxHorairePersonnel> result = new ArrayList<>();
        String query = "SELECT * FROM v_taux_horaire_personnel WHERE ? BETWEEN date_debut AND date_fin";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, date);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    VTauxHorairePersonnel item = new VTauxHorairePersonnel();
                    item.setId(resultSet.getInt("id"));
                    item.setIdPersonnel(resultSet.getInt("id_personnel"));
                    item.setNomPersonnel(resultSet.getString("nom_personnel"));
                    item.setPrenomPersonnel(resultSet.getString("prenom_personnel"));
                    item.setNomPoste(resultSet.getString("nom_poste"));
                    item.setNomNiveau(resultSet.getString("nom_niveau"));
                    item.setDateDebut(resultSet.getTimestamp("date_debut").toLocalDateTime());
                    item.setDateFin(resultSet.getTimestamp("date_fin").toLocalDateTime());
                    item.setValeur(resultSet.getDouble("valeur"));
                    item.setMulti(resultSet.getDouble("multi"));
                    item.setTauxHoraire(resultSet.getDouble("taux_horaire"));
                    result.add(item);
                }
            }
        }

        return result;
    }
}
