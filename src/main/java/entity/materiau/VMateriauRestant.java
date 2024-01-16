package entity.materiau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VMateriauRestant {
    Integer id;
    LocalDateTime dateMouvement;
    Integer idMateriau;
    Double quantite;
    Double prixUnitaire;
    public VMateriauRestant(Integer id, LocalDateTime dateMouvement, Integer idMateriau, Double quantite,
            Double prixUnitaire) {
        setId(id);
        setDateMouvement(dateMouvement);
        setIdMateriau(idMateriau);
        setQuantite(quantite);
        setPrixUnitaire(prixUnitaire);
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

    public static List<VMateriauRestant> selectByIdMateriauWhereDateMouvementBefore(Connection connection, Integer idMateriau,LocalDateTime date) throws SQLException {
        List<VMateriauRestant> materiauxRestants = new ArrayList<>();
        String query = "SELECT * FROM v_materiau_restant WHERE id_materiau = ? AND date_mouvement < ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idMateriau);
            statement.setObject(2, date);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    LocalDateTime dateMouvement = resultSet.getObject("date_mouvement", LocalDateTime.class);
                    Double quantite = resultSet.getDouble("quantite");
                    Double prixUnitaire = resultSet.getDouble("prix_unitaire");
                    VMateriauRestant materiauRestant = new VMateriauRestant(id, dateMouvement, idMateriau, quantite, prixUnitaire);
                    materiauxRestants.add(materiauRestant);
                }
            }
        }
        return materiauxRestants;
    }
    
}
