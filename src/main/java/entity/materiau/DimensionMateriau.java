package entity.materiau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DimensionMateriau {
    private Integer id;
    private Double longueur;
    private Double largeur;
    private Double hauteur;

    public DimensionMateriau() {
    }

    public DimensionMateriau(Integer id, Double longueur, Double largeur, Double hauteur) {
        setId(id);
        setLongueur(longueur);
        setLargeur(largeur);
        setHauteur(hauteur);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getHauteur() {
        return hauteur;
    }

    public void setHauteur(Double hauteur) {
        this.hauteur = hauteur;
    }

    public Double getLargeur() {
        return largeur;
    }

    public void setLargeur(Double largeur) {
        this.largeur = largeur;
    }

    public Double getLongueur() {
        return longueur;
    }

    public void setLongueur(Double longueur) {
        this.longueur = longueur;
    }

    public static List<DimensionMateriau> list(Connection connection) throws Exception {
        List<DimensionMateriau> dimensionMateriauList = new ArrayList<>();
        String query = "SELECT id, longueur, largeur, hauteur FROM dimension_materiau";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            Double longueur = resultSet.getDouble("longueur");
            Double largeur = resultSet.getDouble("largeur");
            Double hauteur = resultSet.getDouble("hauteur");
            DimensionMateriau dimensionMateriau = new DimensionMateriau(id, longueur, largeur, hauteur);
            dimensionMateriauList.add(dimensionMateriau);
        }
        resultSet.close();
        statement.close();
        return dimensionMateriauList;
    }

    public void insert(Connection connection) throws Exception {
        String query = "INSERT INTO dimension_materiau (longueur, largeur, hauteur) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setDouble(1, getLongueur());
        statement.setDouble(2, getLargeur());
        statement.setDouble(3, getHauteur());
        statement.executeUpdate();
        statement.close();
    }
}
