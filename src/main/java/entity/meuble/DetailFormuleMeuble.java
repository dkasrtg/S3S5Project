package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DetailFormuleMeuble {
    private Integer id;
    private Integer idFormuleMeuble;
    private Integer idMateriau;
    private Double quantite;

    public DetailFormuleMeuble() {
    }

    public DetailFormuleMeuble(Integer id,Integer idFormuleMeuble, Integer idMateriau, Double quantite) {
        setId(id);
        setIdFormuleMeuble(idFormuleMeuble);
        setIdMateriau(idMateriau);
        setQuantite(quantite);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdFormuleMeuble() {
        return idFormuleMeuble;
    }

    public void setIdFormuleMeuble(Integer idFormuleMeuble) {
        this.idFormuleMeuble = idFormuleMeuble;
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

    public void insert(Connection connection) throws Exception {
        String query = "INSERT INTO detail_formule_meuble (id_formule_meuble, id_materiau, quantite) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, getIdFormuleMeuble());
        statement.setInt(2, getIdMateriau());
        statement.setDouble(3, getQuantite());
        statement.executeUpdate();
        statement.close();
    }
}
