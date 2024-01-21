package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VMouvementMeuble {
    Integer id;
    LocalDateTime dateMouvement;
    Integer idFormuleMeuble;
    Double quantite;
    Double prixTotal;
    Double prixUnitaire;
    Integer typeMouvement;
    Integer idMouvementMere;
    Integer idMeuble;
    String nomMeuble;
    Integer idStyleMeuble;
    Integer idCategorieMeuble;
    String nomStyleMeuble;
    String nomCategorieMeuble;
    Integer idTailleMeuble;
    String nomTailleMeuble;
    Double totalMateriaux;
    Double totalSalaires;
    Integer idDetailVenteMeuble;
    String description;

    public VMouvementMeuble(Integer id, LocalDateTime dateMouvement, Integer idFormuleMeuble, Double quantite,
            Double prixTotal, Double prixUnitaire, Integer typeMouvement, Integer idMouvementMere, Integer idMeuble,
            String nomMeuble, Integer idStyleMeuble, Integer idCategorieMeuble, String nomStyleMeuble,
            String nomCategorieMeuble, Integer idTailleMeuble, String nomTailleMeuble, Double totalMateriaux,
            Double totalSalaires,Integer idDetailVenteMeuble,String description) {
        this.id = id;
        this.dateMouvement = dateMouvement;
        this.idFormuleMeuble = idFormuleMeuble;
        this.quantite = quantite;
        this.prixTotal = prixTotal;
        this.prixUnitaire = prixUnitaire;
        this.typeMouvement = typeMouvement;
        this.idMouvementMere = idMouvementMere;
        this.idMeuble = idMeuble;
        this.nomMeuble = nomMeuble;
        this.idStyleMeuble = idStyleMeuble;
        this.idCategorieMeuble = idCategorieMeuble;
        this.nomStyleMeuble = nomStyleMeuble;
        this.nomCategorieMeuble = nomCategorieMeuble;
        this.idTailleMeuble = idTailleMeuble;
        this.nomTailleMeuble = nomTailleMeuble;
        this.totalMateriaux = totalMateriaux;
        this.totalSalaires = totalSalaires;
        this.idDetailVenteMeuble = idDetailVenteMeuble;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateMouvement() {
        return dateMouvement;
    }

    public void setDateMouvement(LocalDateTime dateMouvement) {
        this.dateMouvement = dateMouvement;
    }

    public Integer getIdFormuleMeuble() {
        return idFormuleMeuble;
    }

    public void setIdFormuleMeuble(Integer idFormuleMeuble) {
        this.idFormuleMeuble = idFormuleMeuble;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Integer getTypeMouvement() {
        return typeMouvement;
    }

    public void setTypeMouvement(Integer typeMouvement) {
        this.typeMouvement = typeMouvement;
    }

    public Integer getIdMouvementMere() {
        return idMouvementMere;
    }

    public void setIdMouvementMere(Integer idMouvementMere) {
        this.idMouvementMere = idMouvementMere;
    }

    public Integer getIdMeuble() {
        return idMeuble;
    }

    public void setIdMeuble(Integer idMeuble) {
        this.idMeuble = idMeuble;
    }

    public String getNomMeuble() {
        return nomMeuble;
    }

    public void setNomMeuble(String nomMeuble) {
        this.nomMeuble = nomMeuble;
    }

    public Integer getIdStyleMeuble() {
        return idStyleMeuble;
    }

    public void setIdStyleMeuble(Integer idStyleMeuble) {
        this.idStyleMeuble = idStyleMeuble;
    }

    public Integer getIdCategorieMeuble() {
        return idCategorieMeuble;
    }

    public void setIdCategorieMeuble(Integer idCategorieMeuble) {
        this.idCategorieMeuble = idCategorieMeuble;
    }

    public String getNomStyleMeuble() {
        return nomStyleMeuble;
    }

    public void setNomStyleMeuble(String nomStyleMeuble) {
        this.nomStyleMeuble = nomStyleMeuble;
    }

    public String getNomCategorieMeuble() {
        return nomCategorieMeuble;
    }

    public void setNomCategorieMeuble(String nomCategorieMeuble) {
        this.nomCategorieMeuble = nomCategorieMeuble;
    }

    public Integer getIdTailleMeuble() {
        return idTailleMeuble;
    }

    public void setIdTailleMeuble(Integer idTailleMeuble) {
        this.idTailleMeuble = idTailleMeuble;
    }

    public String getNomTailleMeuble() {
        return nomTailleMeuble;
    }

    public void setNomTailleMeuble(String nomTailleMeuble) {
        this.nomTailleMeuble = nomTailleMeuble;
    }

    public void setTotalMateriaux(Double totalMateriaux) {
        this.totalMateriaux = totalMateriaux;
    }

    public Double getTotalMateriaux() {
        return totalMateriaux;
    }

    public void setTotalSalaires(Double totalSalaires) {
        this.totalSalaires = totalSalaires;
    }

    public Double getTotalSalaires() {
        return totalSalaires;
    }

    public void setIdDetailVenteMeuble(Integer idDetailVenteMeuble) {
        this.idDetailVenteMeuble = idDetailVenteMeuble;
    }

    public Integer getIdDetailVenteMeuble() {
        return idDetailVenteMeuble;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public static List<VMouvementMeuble> selectByTypeMouvement(Connection connection, Integer typeMouvement,
            LocalDateTime dateDebut, LocalDateTime dateFin) throws SQLException {
        List<VMouvementMeuble> mouvements = new ArrayList<>();
        String query = "SELECT * FROM v_mouvement_meuble WHERE type_mouvement = ? AND date_mouvement >= ? AND date_mouvement <= ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, typeMouvement);
            statement.setObject(2, dateDebut);
            statement.setObject(3, dateFin);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    LocalDateTime dateMouvement = resultSet.getTimestamp("date_mouvement").toLocalDateTime();
                    Integer idFormuleMeuble = resultSet.getInt("id_formule_meuble");
                    Double quantite = resultSet.getDouble("quantite");
                    Double prixTotal = resultSet.getDouble("prix_total");
                    Double prixUnitaire = resultSet.getDouble("prix_unitaire");
                    Integer typeMouvementResult = resultSet.getInt("type_mouvement");
                    Integer idMouvementMere = resultSet.getInt("id_mouvement_mere");
                    Integer idMeuble = resultSet.getInt("id_meuble");
                    String nomMeuble = resultSet.getString("nom_meuble");
                    Integer idStyleMeuble = resultSet.getInt("id_style_meuble");
                    Integer idCategorieMeuble = resultSet.getInt("id_categorie_meuble");
                    String nomStyleMeuble = resultSet.getString("nom_style_meuble");
                    String nomCategorieMeuble = resultSet.getString("nom_categorie_meuble");
                    Integer idTailleMeuble = resultSet.getInt("id_taille_meuble");
                    String nomTailleMeuble = resultSet.getString("nom_taille_meuble");
                    Double totalMateriaux = resultSet.getDouble("total_materiaux");
                    Double totalSalaires = resultSet.getDouble("total_salaires");
                    Integer idDetailVenteMeuble = resultSet.getInt("id_detail_vente_meuble");
                    String description = resultSet.getString("description");

                    VMouvementMeuble mouvement = new VMouvementMeuble(
                            id, dateMouvement, idFormuleMeuble, quantite, prixTotal, prixUnitaire,
                            typeMouvementResult, idMouvementMere, idMeuble, nomMeuble, idStyleMeuble,
                            idCategorieMeuble, nomStyleMeuble, nomCategorieMeuble, idTailleMeuble,
                            nomTailleMeuble,totalMateriaux,totalSalaires,idDetailVenteMeuble,description);
                    mouvements.add(mouvement);
                }
            }
        }

        return mouvements;
    }

}
