package entity.employe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Employe {
    Integer id;
    String nom;

    public Employe(Integer id, String nom) {
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
        String query = "INSERT INTO employe (nom) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, getNom());
        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            setId(generatedKeys.getInt(1));
        }
    }

    public static List<Employe> list(Connection connection) throws Exception {
        List<Employe> employes = new ArrayList<>();
        String query = "SELECT * FROM employe";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            Employe employe = new Employe(id, nom);
            employes.add(employe);
        }
        statement.close();
        resultSet.close();
        return employes;
    }

}
