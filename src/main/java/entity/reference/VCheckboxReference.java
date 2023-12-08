package entity.reference;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VCheckboxReference {
    Integer id;
    Integer idCheckbox;
    Integer idReference;
    String nom;

    public VCheckboxReference() {
    }

    public VCheckboxReference(Integer id, Integer idCheckbox, Integer idReference, String nom) {
        setId(id);
        setIdCheckbox(idCheckbox);
        setIdReference(idReference);
        setNom(nom);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCheckbox() {
        return idCheckbox;
    }

    public void setIdCheckbox(Integer idCheckbox) {
        this.idCheckbox = idCheckbox;
    }

    public Integer getIdReference() {
        return idReference;
    }

    public void setIdReference(Integer idReference) {
        this.idReference = idReference;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static List<VCheckboxReference> selectByIdReference(Connection connection, Integer idReference) {
        List<VCheckboxReference> checkboxes = new ArrayList<>();

        try {
            String sql = "SELECT id, id_checkbox, id_reference, nom FROM v_checkbox_reference WHERE id_reference = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idReference);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer idCheckbox = resultSet.getInt("id_checkbox");
                Integer referenceId = resultSet.getInt("id_reference");
                String nom = resultSet.getString("nom");

                VCheckboxReference checkbox = new VCheckboxReference(id, idCheckbox, referenceId, nom);
                checkboxes.add(checkbox);
            }

            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return checkboxes;
    }

}
