package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComposantMeuble {
    private Integer id;
    private String nom;
    private Integer idMeuble;
    private Integer idTypeMateriau;
    private Double volume;

    public ComposantMeuble() {
    }

    public ComposantMeuble(Integer id, String nom, Integer idMeuble, Integer idTypeMateriau, Double volume) {
        setId(id);
        setNom(nom);
        setIdMeuble(idMeuble);
        setIdTypeMateriau(idTypeMateriau);
        setVolume(volume);
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

    public Integer getIdMeuble() {
        return idMeuble;
    }

    public void setIdMeuble(Integer idMeuble) {
        this.idMeuble = idMeuble;
    }

    public Integer getIdTypeMateriau() {
        return idTypeMateriau;
    }

    public void setIdTypeMateriau(Integer idTypeMateriau) {
        this.idTypeMateriau = idTypeMateriau;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO composant_meuble (nom, id_meuble, id_type_materiau, volume) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, this.nom);
        statement.setInt(2, this.idMeuble);
        statement.setInt(3, this.idTypeMateriau);
        statement.setDouble(4, this.volume);
        statement.executeUpdate();
    }

}