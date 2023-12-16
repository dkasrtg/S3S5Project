package entity.materiau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DimensionUnitePossibleMateriau {
    private Integer id;
    private Integer idMateriau;
    private Integer idDimensionMateriau;
    private Integer idUniteMateriau;

    public DimensionUnitePossibleMateriau() {
    }

    public DimensionUnitePossibleMateriau(Integer id, Integer idMateriau, Integer idDimensionMateriau, Integer idUniteMateriau) {
        setId(id);
        setIdMateriau(idMateriau);
        setIdDimensionMateriau(idDimensionMateriau);
        setIdUniteMateriau(idUniteMateriau);
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

    public Integer getIdUniteMateriau() {
        return idUniteMateriau;
    }

    public void setIdUniteMateriau(Integer idUniteMateriau) {
        this.idUniteMateriau = idUniteMateriau;
    }

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO dimension_unite_possible_materiau (id_materiau, id_dimension_materiau, id_unite_materiau) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, getIdMateriau());
        statement.setInt(2, getIdDimensionMateriau());
        statement.setInt(3, getIdUniteMateriau());
        statement.executeUpdate();
        statement.close();
    }
}
