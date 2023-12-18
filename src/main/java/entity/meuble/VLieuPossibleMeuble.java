package entity.meuble;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VLieuPossibleMeuble {
    private Integer id;
    private Integer idMeuble;
    private Integer idLieuMeuble;
    private String nom;

    public VLieuPossibleMeuble() {
    }

    public VLieuPossibleMeuble(Integer id, Integer idMeuble, Integer idLieuMeuble, String nom) {
        setId(id);
        setIdMeuble(idMeuble);
        setIdLieuMeuble(idLieuMeuble);
        setNom(nom);
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static List<VLieuPossibleMeuble> selectByIdMeuble(Connection connection, Integer idMeuble)
            throws SQLException {
        List<VLieuPossibleMeuble> vLieuPossibleMeubleList = new ArrayList<>();
        String query = "SELECT * FROM v_lieu_possible_meuble WHERE id_meuble = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idMeuble);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int idLieuMeuble = resultSet.getInt("id_lieu_meuble");
            String nom = resultSet.getString("nom");
            VLieuPossibleMeuble vLieuPossibleMeuble = new VLieuPossibleMeuble(id, idMeuble, idLieuMeuble, nom);
            vLieuPossibleMeubleList.add(vLieuPossibleMeuble);
        }
        return vLieuPossibleMeubleList;
    }
}
