package entity.reference;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CheckboxReference {
    Integer id;
    Integer idCheckbox;
    Integer idReference;

    public CheckboxReference() {
    }

    public CheckboxReference(Integer id, Integer idCheckbox, Integer idReference) {
        setId(id);
        setIdCheckbox(idCheckbox);
        setIdReference(idReference);
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

    public void insert(Connection connection) {
        try {
            String sql = "INSERT INTO checkbox_reference (id_checkbox, id_reference) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, getIdCheckbox());
            statement.setInt(2, getIdReference());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteByIdReference(Connection connection, Integer idReference) {
        try {
            String sql = "DELETE FROM checkbox_reference WHERE id_reference = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idReference);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
