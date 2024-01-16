package entity.materiau;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Materiau {
    private Integer id;
    private String nom;
    private Integer idTypeMateriau;
    private String description;

    public Materiau() {
    }

    public Materiau(Integer id, String nom, Integer idTypeMateriau, String description) {
        setId(id);
        setNom(nom);
        setIdTypeMateriau(idTypeMateriau);
        setDescription(description);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getIdTypeMateriau() {
        return idTypeMateriau;
    }

    public void setIdTypeMateriau(Integer idTypeMateriau) {
        this.idTypeMateriau = idTypeMateriau;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void insert(Connection connection) throws Exception {
        String query = "INSERT INTO materiau (nom, id_type_materiau, description) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, this.nom);
        statement.setInt(2, this.idTypeMateriau);
        statement.setString(3, this.description);
        statement.executeUpdate();
        statement.close();
    }

}
