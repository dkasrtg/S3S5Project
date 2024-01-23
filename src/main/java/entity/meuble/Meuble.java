package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Meuble {
    private Integer id;
    private String nom;
    private Integer idStyleMeuble;
    private Integer idCategorieMeuble;
    private String description;

    public Meuble() {
    }
    
    public Integer getId() {
        return id;
    }

    public Meuble(Integer id, String nom, Integer idStyleMeuble, Integer idCategorieMeuble, String description) {
        setId(id);
        setNom(nom);
        setIdStyleMeuble(idStyleMeuble);
        setIdCategorieMeuble(idCategorieMeuble);
        setDescription(description);
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

    public Integer getIdStyleMeuble() {
        return idStyleMeuble;
    }

    public void setIdStyleMeuble(Integer idStyleMeuble) {
        this.idStyleMeuble = idStyleMeuble;
    }

    public Integer getIdCategorieMeuble() {
        return idCategorieMeuble;
    }

    public void setIdCategorieMeuble(Integer idCategorieMeuble) {
        this.idCategorieMeuble = idCategorieMeuble;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void insert(Connection connection) throws Exception {
        String query = "INSERT INTO meuble (nom, id_style_meuble, id_categorie_meuble, description) VALUES (?, ?, ?, ?) RETURNING id";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, getNom());
        statement.setInt(2, getIdStyleMeuble());
        statement.setInt(3, getIdCategorieMeuble());
        statement.setString(4, getDescription());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            setId(resultSet.getInt("id"));
        }
        statement.close();
        resultSet.close();
    }
}
