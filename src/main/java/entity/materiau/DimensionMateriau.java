package entity.materiau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DimensionMateriau {
    private Integer id;
    private String dimension;

    public DimensionMateriau() {
    }

    public DimensionMateriau(Integer id, String dimension) {
        setId(id);
        setDimension(dimension);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public static List<DimensionMateriau> list(Connection connection) throws Exception {
        List<DimensionMateriau> dimensionMateriauList = new ArrayList<>();
        String query = "SELECT id, dimension FROM dimension_materiau";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String dimension = resultSet.getString("dimension");
            DimensionMateriau dimensionMateriau = new DimensionMateriau(id, dimension);
            dimensionMateriauList.add(dimensionMateriau);
        }
        resultSet.close();
        statement.close();
        return dimensionMateriauList;
    }

    public void insert(Connection connection) throws Exception {
        String query = "INSERT INTO dimension_materiau (dimension) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, getDimension());
        statement.executeUpdate();
        statement.close();
    }
}
