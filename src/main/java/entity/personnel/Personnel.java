package entity.personnel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Personnel {
    private Integer id;
    private String nom;
    private String prenom;

    public Personnel() {

    }

    public Personnel(Integer id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO personnel (nom, prenom) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, getNom());
            statement.setString(2, getPrenom());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    setId(generatedKeys.getInt(1));
                }
            }
        }
    }
}
