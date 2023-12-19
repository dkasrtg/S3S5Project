package entity.materiau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static boolean hasDimensionUnite(Connection connection, Integer idMateriau, Integer idDimensionMateriau,
            Integer idUniteMateriau)
            throws SQLException {
        String query = "SELECT * FROM dimension_unite_possible_materiau WHERE id_materiau = ? AND id_dimension_materiau = ? and id_unite_materiau = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idMateriau);
        statement.setInt(2, idDimensionMateriau);
        statement.setInt(3, idUniteMateriau);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    public static boolean hasType(Connection connection, Integer idMateriau, Integer idTypeMateriau)
            throws SQLException {
        String query = "SELECT * FROM materiau WHERE id = ? AND id_type_materiau = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idMateriau);
        statement.setInt(2, idTypeMateriau);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

}
