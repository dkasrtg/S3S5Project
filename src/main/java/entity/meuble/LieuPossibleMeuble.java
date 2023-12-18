package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LieuPossibleMeuble {
    private Integer id;
    private Integer idMeuble;
    private Integer idLieuMeuble;

    public LieuPossibleMeuble() {
    }

    public LieuPossibleMeuble(Integer id, Integer idMeuble, Integer idLieuMeuble) {
        setId(id);
        setIdMeuble(idMeuble);
        setIdLieuMeuble(idLieuMeuble);
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

    public Integer getIdLieuMeuble() {
        return idLieuMeuble;
    }

    public void setIdLieuMeuble(Integer idLieuMeuble) {
        this.idLieuMeuble = idLieuMeuble;
    }

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO lieu_possible_meuble (id_meuble, id_lieu_meuble) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, this.idMeuble);
        statement.setInt(2, this.idLieuMeuble);
        statement.executeUpdate();
        statement.close();
    }
}
