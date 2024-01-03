package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StyleMeuble {
    private Integer id;
    private String nom;

    public StyleMeuble() {
    }

    public StyleMeuble(Integer id, String nom) {
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
        String query = "INSERT INTO style_meuble (nom) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, this.nom);
        statement.executeUpdate();
        statement.close();
    }

    public static List<StyleMeuble> list(Connection connection) throws SQLException {
        List<StyleMeuble> styleMeubleList = new ArrayList<>();
        String query = "SELECT * FROM style_meuble";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            StyleMeuble styleMeuble = new StyleMeuble(id, nom);
            styleMeubleList.add(styleMeuble);
        }
        statement.close();
        resultSet.close();
        return styleMeubleList;
    }

    public static StyleMeuble selectById(Connection connection, int id) throws SQLException {
        String query = "SELECT * FROM style_meuble WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        StyleMeuble styleMeuble = null;
        if (resultSet.next()) {
            String nom = resultSet.getString("nom");
            styleMeuble = new StyleMeuble(id, nom);
        }
        statement.close();
        resultSet.close();
        return styleMeuble;
    }
}