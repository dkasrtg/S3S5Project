package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VMateriauPossibleStyleMeuble {
    private Integer id;
    private Integer idStyleMeuble;
    private Integer idMateriau;
    private String nomMateriau;
    private Integer idTypeMateriau;
    private String nomTypeMateriau;

    public VMateriauPossibleStyleMeuble() {
    }

    public VMateriauPossibleStyleMeuble(Integer id, Integer idStyleMeuble, Integer idMateriau,
                                       String nomMateriau, Integer idTypeMateriau, String nomTypeMateriau) {
        setId(id);
        setIdStyleMeuble(idStyleMeuble);
        setIdMateriau(idMateriau);
        setNomMateriau(nomMateriau);
        setIdTypeMateriau(idTypeMateriau);
        setNomTypeMateriau(nomTypeMateriau);
    }

    public Integer getIdMateriau() {
        return idMateriau;
    }

    public Integer getIdStyleMeuble() {
        return idStyleMeuble;
    }

    public Integer getIdTypeMateriau() {
        return idTypeMateriau;
    }
    public String getNomTypeMateriau() {
        return nomTypeMateriau;
    }

    public String getNomMateriau() {
        return nomMateriau;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdMateriau(Integer idMateriau) {
        this.idMateriau = idMateriau;
    }

    public void setIdStyleMeuble(Integer idStyleMeuble) {
        this.idStyleMeuble = idStyleMeuble;
    }

    public void setIdTypeMateriau(Integer idTypeMateriau) {
        this.idTypeMateriau = idTypeMateriau;
    }

    public void setNomMateriau(String nomMateriau) {
        this.nomMateriau = nomMateriau;
    }

    public void setNomTypeMateriau(String nomTypeMateriau) {
        this.nomTypeMateriau = nomTypeMateriau;
    }

    public static List<VMateriauPossibleStyleMeuble> selectByIdStyleMeuble(Connection connection, int idStyleMeuble) throws SQLException {
        List<VMateriauPossibleStyleMeuble> materiauList = new ArrayList<>();
        String query = "SELECT * FROM public.v_materiau_possible_style_meuble WHERE id_style_meuble = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idStyleMeuble);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            Integer idMateriau = resultSet.getInt("id_materiau");
            String nomMateriau = resultSet.getString("nom_materiau");
            Integer idTypeMateriau = resultSet.getInt("id_type_materiau");
            String nomTypeMateriau = resultSet.getString("nom_type_materiau");

            VMateriauPossibleStyleMeuble materiau = new VMateriauPossibleStyleMeuble(id, idStyleMeuble, idMateriau, nomMateriau, idTypeMateriau, nomTypeMateriau);
            materiauList.add(materiau);
        }
        statement.close();
        resultSet.close();
        return materiauList;
    }
}
