package entity.materiau;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UniteMateriau {
    private Integer id;
    private String nom;

    public UniteMateriau() {
    }

    public UniteMateriau(Integer id, String nom) {
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
        String query = "INSERT INTO unite_materiau (nom) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, this.nom);
        statement.executeUpdate();
        statement.close();
    }

    public static List<UniteMateriau> list(Connection connection) throws Exception {
        List<UniteMateriau> uniteMateriauList = new ArrayList<>();
        String query = "SELECT * FROM unite_materiau";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            UniteMateriau uniteMateriau = new UniteMateriau(id, nom);
            uniteMateriauList.add(uniteMateriau);
        }
        statement.close();
        resultSet.close();
        return uniteMateriauList;
    }
}
