package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategorieMeuble {
    private Integer id;
    private String nom;

    public CategorieMeuble() {
    }

    public CategorieMeuble(Integer id, String nom) {
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

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO categorie_meuble (nom) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, this.nom);
        statement.executeUpdate();
        statement.close();
    }

    public static List<CategorieMeuble> list(Connection connection) throws SQLException {
        List<CategorieMeuble> categorieMeubleList = new ArrayList<>();
        String query = "SELECT * FROM categorie_meuble";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            CategorieMeuble categorieMeuble = new CategorieMeuble(id, nom);
            categorieMeubleList.add(categorieMeuble);
        }
        return categorieMeubleList;
    }
}
