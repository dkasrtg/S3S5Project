package entity.materiau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TypeMateriau {
    private Integer id;
    private String nom;

    public TypeMateriau() {
    }

    public TypeMateriau(Integer id, String nom) {
        setId(id);
        setNom(nom);
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

    public void insert(Connection connection) throws Exception {
        String query = "INSERT INTO type_materiau (nom) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, getNom());
        statement.executeUpdate();
        statement.close();
    }

    public static List<TypeMateriau> list(Connection connection) throws Exception {
        List<TypeMateriau> typeMateriauList = new ArrayList<>();
        String query = "SELECT id, nom FROM type_materiau";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            TypeMateriau typeMateriau = new TypeMateriau(id, nom);
            typeMateriauList.add(typeMateriau);
        }
        statement.close();
        resultSet.close();
        return typeMateriauList;
    }

}
