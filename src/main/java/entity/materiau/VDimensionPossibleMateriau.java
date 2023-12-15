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
    private String dimension;

    public VDimensionPossibleMateriau() {
    }

    public VDimensionPossibleMateriau(Integer id, Integer idMateriau, Integer idDimensionMateriau, String dimension) {
        setId(id);
        setIdMateriau(idMateriau);
        setIdDimensionMateriau(idDimensionMateriau);
        setDimension(dimension);
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

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public static List<VDimensionPossibleMateriau> listByIdMateriau(Connection connection, Integer idMateriau)
            throws Exception {
        List<VDimensionPossibleMateriau> dimensionPossibleMateriauList = new ArrayList<>();
        String query = "SELECT * from v_dimension_possible_materiau where id_materiau = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idMateriau);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int idDimensionMateriau = resultSet.getInt("id_dimension_materiau");
            String dimension = resultSet.getString("dimension");
            VDimensionPossibleMateriau dimensionPossibleMateriau = new VDimensionPossibleMateriau(id, idMateriau,
                    idDimensionMateriau, dimension);
            dimensionPossibleMateriauList.add(dimensionPossibleMateriau);
        }
        return dimensionPossibleMateriauList;
    }

}
