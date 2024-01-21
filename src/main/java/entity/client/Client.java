package entity.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private Integer id;
    private String nom;
    private String prenom;
    private String telephone;

    public Client() {
    }

    public Client(Integer id, String nom, String prenom, String telephone) {
        setId(id);
        setNom(nom);
        setPrenom(prenom);
        setTelephone(telephone);
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO client (nom, prenom, telephone) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, getNom());
            statement.setString(2, getPrenom());
            statement.setString(3, getTelephone());
            statement.executeUpdate();
        }
    }
    public static List<Client> list(Connection connection) throws SQLException {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM client";
        
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String telephone = resultSet.getString("telephone");

                Client client = new Client(id, nom, prenom, telephone);
                clients.add(client);
            }
        }

        return clients;
    }
}
