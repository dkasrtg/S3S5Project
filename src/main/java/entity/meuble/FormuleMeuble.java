package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormuleMeuble {
    private Integer id;
    private Integer idMeuble;
    private Integer idTailleMeuble;

    public FormuleMeuble() {
    }

    public FormuleMeuble(Integer id, Integer idMeuble, Integer idTailleMeuble) {
        setId(id);
        setIdMeuble(idMeuble);
        setIdTailleMeuble(idTailleMeuble);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdMeuble() {
        return idMeuble;
    }

    public void setIdMeuble(Integer idMeuble) {
        this.idMeuble = idMeuble;
    }

    public Integer getIdTailleMeuble() {
        return idTailleMeuble;
    }

    public void setIdTailleMeuble(Integer idTailleMeuble) {
        this.idTailleMeuble = idTailleMeuble;
    }

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO formule_meuble (id_meuble, id_taille_meuble) VALUES (?, ?) RETURNING id";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, getIdMeuble());
        statement.setInt(2, getIdTailleMeuble());
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            setId(rs.getInt("id"));
        }
        rs.close();
        statement.close();
    }

    public static int existByIdMeubleAndTailleMeuble(Connection connection, int idMeuble, int idTailleMeuble)
            throws SQLException {
        int result = -1;
        String query = "SELECT * FROM formule_meuble WHERE id_meuble = ? AND id_taille_meuble = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idMeuble);
        statement.setInt(2, idTailleMeuble);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            result = resultSet.getInt("id");
        }
        statement.close();
        resultSet.close();
        return result;
    }

}
