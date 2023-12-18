package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VComposantMeuble {
    private Integer id;
    private String nom;
    private Integer idMeuble;
    private Integer idTypeMateriau;
    private Double volume;
    private String nomTypeMateriau;

    public VComposantMeuble() {
    }

    public VComposantMeuble(Integer id, String nom, Integer idMeuble, Integer idTypeMateriau, Double volume,
            String nomTypeMateriau) {
        setId(id);
        setNom(nom);
        setIdMeuble(idMeuble);
        setIdTypeMateriau(idTypeMateriau);
        setVolume(volume);
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

    public Integer getIdMeuble() {
        return idMeuble;
    }

    public void setIdMeuble(Integer idMeuble) {
        this.idMeuble = idMeuble;
    }

    public Integer getIdTypeMateriau() {
        return idTypeMateriau;
    }

    public void setIdTypeMateriau(Integer idTypeMateriau) {
        this.idTypeMateriau = idTypeMateriau;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public String getNomTypeMateriau() {
        return nomTypeMateriau;
    }

    public void setNomTypeMateriau(String nomTypeMateriau) {
        this.nomTypeMateriau = nomTypeMateriau;
    }

    public static List<VComposantMeuble> selectByIdMeuble(Connection connection, Integer idMeuble) throws SQLException {
        List<VComposantMeuble> composants = new ArrayList<>();
        String query = "SELECT * FROM v_composant_meuble WHERE id_meuble = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idMeuble);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            Integer typeIdMateriau = resultSet.getInt("id_type_materiau");
            Double volume = resultSet.getDouble("volume");
            String nomTypeMateriau = resultSet.getString("nom_type_materiau");
            VComposantMeuble composant = new VComposantMeuble(id, nom, idMeuble, typeIdMateriau, volume,
                    nomTypeMateriau);
            composants.add(composant);
        }
        return composants;
    }
}
