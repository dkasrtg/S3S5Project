package entity.materiau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import com.genericdao.GenericDAO;
import com.genericdao.annotation.Column;
import com.genericdao.annotation.Table;

@Table(name = "v_materiau_restant")
public class VMateriauRestant extends GenericDAO {

    @Column(name = "id")
    private Integer id;

    @Column(name = "date_mouvement")
    private LocalDateTime dateMouvement;

    @Column(name = "id_materiau")
    private Integer idMateriau;

    @Column(name = "quantite")
    private Double quantite;

    @Column(name = "prix_unitaire")
    private Double prixUnitaire;

    public VMateriauRestant() {

    }

    public VMateriauRestant(Integer id, LocalDateTime dateMouvement, Integer idMateriau, Double quantite,
            Double prixUnitaire) {
        setId(id);
        setDateMouvement(dateMouvement);
        setIdMateriau(idMateriau);
        setQuantite(quantite);
        setPrixUnitaire(prixUnitaire);

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setDateMouvement(LocalDateTime dateMouvement) {
        this.dateMouvement = dateMouvement;
    }

    public LocalDateTime getDateMouvement() {
        return dateMouvement;
    }

    public void setIdMateriau(Integer idMateriau) {
        this.idMateriau = idMateriau;
    }

    public Integer getIdMateriau() {
        return idMateriau;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public static List<VMateriauRestant> selectByIdMateriauWhereDateMouvementBefore(Connection connection,
            Integer idMateriau, LocalDateTime date) throws Exception {
        String query = "SELECT * FROM v_materiau_restant WHERE id_materiau = ? AND date_mouvement < ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idMateriau);
        statement.setObject(2, date);
        return VMateriauRestant.selectMultipleByPreparedStatement(VMateriauRestant.class, statement, connection);
    }

    public static HashMap<Integer, Double> getGlobalRest(Connection connection) throws Exception {
        HashMap<Integer, Double> globalRestMap = new HashMap<>();
        String query = "SELECT id_materiau, SUM(quantite) as quantite FROM v_materiau_restant GROUP BY id_materiau";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int idMateriau = resultSet.getInt("id_materiau");
            double quantite = resultSet.getDouble("quantite");
            globalRestMap.put(idMateriau, quantite);
        }
        statement.close();
        resultSet.close();
        return globalRestMap;
    }

}