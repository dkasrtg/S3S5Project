package entity.materiau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DimensionPossibleMateriau {
    private Integer id;
    private Integer idMateriau;
    private Integer idDimensionMateriau;

    public DimensionPossibleMateriau() {
    }

    public DimensionPossibleMateriau(Integer id, Integer idMateriau, Integer idDimensionMateriau) {
        setId(id);
        setIdMateriau(idMateriau);
        setIdDimensionMateriau(idDimensionMateriau);
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

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO dimension_possible_materiau (id_materiau, id_dimension_materiau) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, this.idMateriau);
        statement.setInt(2, this.idDimensionMateriau);
        statement.executeUpdate();
        statement.close();
    }
}
