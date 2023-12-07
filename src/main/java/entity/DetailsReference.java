package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DetailsReference {
    Integer id;
    Integer idReference;
    String details;
    Double note;

    public DetailsReference() {
    }

    public DetailsReference(Integer id, Integer idReference, String details, Double note) {
        setId(id);
        setIdReference(idReference);
        setDetails(details);
        setNote(note);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdReference() {
        return idReference;
    }

    public void setIdReference(Integer idReference) {
        this.idReference = idReference;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public static List<DetailsReference> selectByIdReference(Connection connection, Integer idReference) {
        List<DetailsReference> detailsReferences = new ArrayList<>();

        try {
            String sql = "SELECT id, id_reference, details, note FROM details_reference WHERE id_reference = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idReference);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer referenceId = resultSet.getInt("id_reference");
                String details = resultSet.getString("details");
                Double note = resultSet.getDouble("note");

                DetailsReference detailsReference = new DetailsReference(id, referenceId, details, note);
                detailsReferences.add(detailsReference);
            }

            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return detailsReferences;
    }

    public void insert(Connection connection) {
        try {
            String sql = "INSERT INTO details_reference (id_reference, details, note) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, getIdReference());
            statement.setString(2, getDetails());
            statement.setDouble(3, getNote());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
