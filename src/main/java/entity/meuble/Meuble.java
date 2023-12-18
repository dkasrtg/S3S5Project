package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Meuble {
    private Integer id;
    private String nom;
    private Integer idStyleMeuble;
    private Integer idCategorieMeuble;
    private Double longueur;
    private Double largeur;
    private Double hauteur;
    private Double volume;
    private Double volumeMateriau;
    private String description;

    public Meuble() {
    }

    public Meuble(Integer id, String nom, Integer idStyleMeuble, Integer idCategorieMeuble, Double longueur,
            Double largeur,
            Double hauteur, Double volume, Double volumeMateriau, String description) {
        setId(id);
        setNom(nom);
        setIdStyleMeuble(idStyleMeuble);
        setIdCategorieMeuble(idCategorieMeuble);
        setLongueur(longueur);
        setLargeur(largeur);
        setHauteur(hauteur);
        setVolume(volume);
        setVolumeMateriau(volumeMateriau);
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

    public Double getLongueur() {
        return longueur;
    }

    public void setLongueur(Double longueur) {
        this.longueur = longueur;
    }

    public Double getLargeur() {
        return largeur;
    }

    public void setLargeur(Double largeur) {
        this.largeur = largeur;
    }

    public Double getHauteur() {
        return hauteur;
    }

    public void setHauteur(Double hauteur) {
        this.hauteur = hauteur;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getVolumeMateriau() {
        return volumeMateriau;
    }

    public void setVolumeMateriau(Double volumeMateriau) {
        this.volumeMateriau = volumeMateriau;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO meuble (nom, id_style_meuble, id_categorie_meuble, longueur, largeur, hauteur, volume, volume_materiau, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, getNom());
        statement.setInt(2, getIdStyleMeuble());
        statement.setInt(3, getIdCategorieMeuble());
        statement.setDouble(4, getLongueur());
        statement.setDouble(5, getLargeur());
        statement.setDouble(6, getHauteur());
        statement.setDouble(7, getVolume());
        statement.setDouble(8, getVolumeMateriau());
        statement.setString(9, getDescription());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            setId(resultSet.getInt("id"));
        }
        statement.close();
        resultSet.close();
    }
}
