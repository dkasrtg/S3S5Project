package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MateriauPossibleStyleMeuble {
    private Integer id;
    private Integer idStyleMeuble;
    private Integer idMateriau;

    public MateriauPossibleStyleMeuble() {
    }

    public MateriauPossibleStyleMeuble(Integer id, Integer idStyleMeuble, Integer idMateriau) {
        setId(id);
        setIdStyleMeuble(idStyleMeuble);
        setIdMateriau(idMateriau);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdStyleMeuble() {
        return idStyleMeuble;
    }

    public void setIdStyleMeuble(Integer idStyleMeuble) {
        this.idStyleMeuble = idStyleMeuble;
    }

    public Integer getIdMateriau() {
        return idMateriau;
    }

    public void setIdMateriau(Integer idMateriau) {
        this.idMateriau = idMateriau;
    }

    public void insert(Connection connection) throws Exception {
        String query = "INSERT INTO materiau_possible_style_meuble (id_style_meuble, id_materiau) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, this.idStyleMeuble);
        statement.setInt(2, this.idMateriau);
        statement.executeUpdate();
        statement.close();
    }

    
}
