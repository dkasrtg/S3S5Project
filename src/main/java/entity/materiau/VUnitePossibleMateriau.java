package entity.materiau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VUnitePossibleMateriau {
    private Integer idMateriau;
    private Integer idUniteMateriau;
    private String nomUniteMateriau;

    public VUnitePossibleMateriau() {
    }

    public VUnitePossibleMateriau(Integer idMateriau, Integer idUniteMateriau, String nomUniteMateriau) {
        setIdMateriau(idMateriau);
        setIdUniteMateriau(idUniteMateriau);
        setNomUniteMateriau(nomUniteMateriau);
    }

    public Integer getIdMateriau() {
        return idMateriau;
    }

    public void setIdMateriau(Integer idMateriau) {
        this.idMateriau = idMateriau;
    }

    public Integer getIdUniteMateriau() {
        return idUniteMateriau;
    }

    public void setIdUniteMateriau(Integer idUniteMateriau) {
        this.idUniteMateriau = idUniteMateriau;
    }

    public String getNomUniteMateriau() {
        return nomUniteMateriau;
    }

    public void setNomUniteMateriau(String nomUniteMateriau) {
        this.nomUniteMateriau = nomUniteMateriau;
    }

    public static List<VUnitePossibleMateriau> listByIdMateriau(Connection connection, Integer idMateriau)
            throws Exception {
        List<VUnitePossibleMateriau> vUnitePossibleMateriauList = new ArrayList<>();
        String query = "SELECT * from v_unite_possible_materiau where id_materiau = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idMateriau);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Integer idUniteMateriau = resultSet.getInt("id_unite_materiau");
            String nomUniteMateriau = resultSet.getString("nom_unite_materiau");
            VUnitePossibleMateriau vUnitePossibleMateriau = new VUnitePossibleMateriau(idMateriau, idUniteMateriau, nomUniteMateriau);
            vUnitePossibleMateriauList.add(vUnitePossibleMateriau);
        }
        return vUnitePossibleMateriauList;
    }

}