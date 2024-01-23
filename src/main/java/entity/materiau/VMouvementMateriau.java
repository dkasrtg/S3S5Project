package entity.materiau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VMouvementMateriau {
    Integer id;
    LocalDateTime dateMouvement;
    Integer idMateriau;
    String nomMateriau;
    Integer idTypeMateriau;
    String nomTypeMateriau;
    Double quantite;
    Double prixUnitaire;
    Integer typeMouvement;
    String nomTypeMouvement;
    Integer idMouvementMere;
    String description;
    Double prixTotal;
    Integer idMouvementMeuble;

    public VMouvementMateriau() {
    }

    public VMouvementMateriau(Integer id, LocalDateTime dateMouvement, Integer idMateriau, String nomMateriau,
            Integer idTypeMateriau, String nomTypeMateriau, Double quantite, Double prixUnitaire, Integer typeMouvement,
            Integer idMouvementMere, String description, Double prixTotal, Integer idMouvementMeuble) {
        setId(id);
        setDateMouvement(dateMouvement);
        setIdMateriau(idMateriau);
        setNomMateriau(nomMateriau);
        setQuantite(quantite);
        setPrixUnitaire(prixUnitaire);
        setTypeMouvement(typeMouvement);
        setIdMouvementMere(idMouvementMere);
        setIdTypeMateriau(idTypeMateriau);
        setNomTypeMateriau(nomTypeMateriau);
        setDescription(description);
        setNomTypeMouvement();
        setPrixTotal(prixTotal);
        setIdMouvementMeuble(idMouvementMeuble);
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

    public Integer getIdMateriau() {
        return idMateriau;
    }

    public void setIdMateriau(Integer idMateriau) {
        this.idMateriau = idMateriau;
    }

    public String getNomMateriau() {
        return nomMateriau;
    }

    public void setNomMateriau(String nomMateriau) {
        this.nomMateriau = nomMateriau;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
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

    public void setNomTypeMouvement() {
        if (getTypeMouvement() == 1) {
            this.nomTypeMouvement = "entree";
            return;
        }
        this.nomTypeMouvement = "sortie";
    }

    public Integer getIdMouvementMere() {
        return idMouvementMere;
    }

    public void setIdMouvementMere(Integer idMouvementMere) {
        this.idMouvementMere = idMouvementMere;
    }

    public void setIdTypeMateriau(Integer idTypeMateriau) {
        this.idTypeMateriau = idTypeMateriau;
    }

    public Integer getIdTypeMateriau() {
        return idTypeMateriau;
    }

    public void setNomTypeMateriau(String nomTypeMateriau) {
        this.nomTypeMateriau = nomTypeMateriau;
    }

    public String getNomTypeMateriau() {
        return nomTypeMateriau;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setIdMouvementMeuble(Integer idMouvementMeuble) {
        this.idMouvementMeuble = idMouvementMeuble;
    }

    public Integer getIdMouvementMeuble() {
        return idMouvementMeuble;
    }

    public static List<VMouvementMateriau> selectByTypeMouvement(Connection connection, Integer typeMouvement,
            LocalDateTime dateDebut, LocalDateTime dateFin) throws Exception {
        List<VMouvementMateriau> mouvements = new ArrayList<>();
        String query = "select * from v_mouvement_materiau where type_mouvement= ? and date_mouvement>= ? and date_mouvement<= ? ";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, typeMouvement);
        statement.setObject(2, dateDebut);
        statement.setObject(3, dateFin);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            LocalDateTime dateMouvement = resultSet.getTimestamp("date_mouvement").toLocalDateTime();
            Integer idMateriau = resultSet.getInt("id_materiau");
            String nomMateriau = resultSet.getString("nom_materiau");
            Integer idTypeMateriau = resultSet.getInt("id_type_materiau");
            String nomTypeMateriau = resultSet.getString("nom_type_materiau");
            Double quantite = resultSet.getDouble("quantite");
            Double prixUnitaire = resultSet.getDouble("prix_unitaire");
            Integer typeMvt = resultSet.getInt("type_mouvement");
            Integer idMouvementMere = resultSet.getInt("id_mouvement_mere");
            String description = resultSet.getString("description");
            Double prixTotal = resultSet.getDouble("prix_total");
            Integer idMouvementMeuble = resultSet.getInt("id_mouvement_meuble");
            VMouvementMateriau mouvement = new VMouvementMateriau(
                    id, dateMouvement, idMateriau, nomMateriau, idTypeMateriau, nomTypeMateriau,
                    quantite, prixUnitaire, typeMvt, idMouvementMere, description, prixTotal,
                    idMouvementMeuble);
            mouvements.add(mouvement);
        }
        statement.close();
        resultSet.close();
        return mouvements;
    }

}
