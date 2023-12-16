package entity.materiau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VDimensionPossibleMateriau {
    private Integer id;
    private Integer idMateriau;
    private Integer idDimensionMateriau;
    private Double longueur;
    private Double largeur;
    private Double hauteur;

    public VDimensionPossibleMateriau() {
    }

    public VDimensionPossibleMateriau(Integer id, Integer idMateriau, Integer idDimensionMateriau, Double longueur, Double largeur, Double hauteur) {
        setId(id);
        setIdMateriau(idMateriau);
        setIdDimensionMateriau(idDimensionMateriau);
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

    public Integer getIdMateriau() {
        return idMateriau;
    }

    public void setIdMateriau(Integer idMateriau) {
        this.idMateriau = idMateriau;
    }

    public Integer getIdDimensionMateriau() {
        return idDimensionMateriau;
    }

    public void setIdDimensionMateriau(Integer idDimensionMateriau) {
        this.idDimensionMateriau = idDimensionMateriau;
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

    public static List<VDimensionPossibleMateriau> listByIdMateriau(Connection connection, Integer idMateriau)
            throws Exception {
        List<VDimensionPossibleMateriau> dimensionPossibleMateriauList = new ArrayList<>();
        String query = "SELECT * from v_dimension_possible_materiau where id_materiau = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idMateriau);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            Integer idDimensionMateriau = resultSet.getInt("id_dimension_materiau");
            Double longueur = resultSet.getDouble("longueur");
            Double largeur = resultSet.getDouble("largeur");
            Double hauteur = resultSet.getDouble("hauteur");
            VDimensionPossibleMateriau dimensionPossibleMateriau = new VDimensionPossibleMateriau(id, idMateriau,
                    idDimensionMateriau, longueur, largeur , hauteur);
            dimensionPossibleMateriauList.add(dimensionPossibleMateriau);
        }
        return dimensionPossibleMateriauList;
    }

}