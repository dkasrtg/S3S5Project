package entity.materiau;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VMateriau {
    private Integer id;
    private String nom;
    private Integer idTypeMateriau;
    private String description;
    private String nomTypeMateriau;
    private List<VDimensionPossibleMateriau> vDimensionPossibleMateriau;
    private List<VUnitePossibleMateriau> vUnitePossibleMateriau;

    public VMateriau() {
    }

    public VMateriau(Integer id, String nom, Integer idTypeMateriau, String description, String nomTypeMateriau) {
        setId(id);
        setNom(nom);
        setIdTypeMateriau(idTypeMateriau);
        setDescription(description);
        setNomTypeMateriau(nomTypeMateriau);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getIdTypeMateriau() {
        return idTypeMateriau;
    }

    public void setIdTypeMateriau(Integer idTypeMateriau) {
        this.idTypeMateriau = idTypeMateriau;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNomTypeMateriau() {
        return nomTypeMateriau;
    }

    public void setNomTypeMateriau(String nomTypeMateriau) {
        this.nomTypeMateriau = nomTypeMateriau;
    }

    public List<VDimensionPossibleMateriau> getVDimensionPossibleMateriau() {
        return vDimensionPossibleMateriau;
    }

    public void setVDimensionPossibleMateriau(List<VDimensionPossibleMateriau> vDimensionPossibleMateriau) {
        this.vDimensionPossibleMateriau = vDimensionPossibleMateriau;
    }

    public void setVUnitePossibleMateriau(List<VUnitePossibleMateriau> vUnitePossibleMateriau) {
        this.vUnitePossibleMateriau = vUnitePossibleMateriau;
    }

    public List<VUnitePossibleMateriau> getVUnitePossibleMateriau() {
        return vUnitePossibleMateriau;
    }

    public static List<VMateriau> list(Connection connection) throws Exception {
        List<VMateriau> vMateriauList = new ArrayList<>();
        String query = "SELECT * FROM v_materiau";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            Integer idTypeMateriau = resultSet.getInt("id_type_materiau");
            String description = resultSet.getString("description");
            String nomTypeMateriau = resultSet.getString("nom_type_materiau");
            VMateriau vMateriau = new VMateriau(id, nom, idTypeMateriau, description, nomTypeMateriau);
            vMateriauList.add(vMateriau);
        }
        statement.close();
        resultSet.close();
        return vMateriauList;
    }
}
