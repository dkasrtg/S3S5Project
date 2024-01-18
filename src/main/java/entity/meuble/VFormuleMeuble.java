package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VFormuleMeuble {
    private Integer id;
    private Integer idMeuble;
    private Integer idTailleMeuble;
    private String nomTailleMeuble;
    private List<VDetailFormuleMeuble> vDetailFormuleMeubles;
    private List<VDetailEmployeMeuble> vDetailEmployeMeubles;


    public VFormuleMeuble() {
    }

    public VFormuleMeuble(Integer id, Integer idMeuble, Integer idTailleMeuble, String nomTailleMeuble) {
        setId(id);
        setIdMeuble(idMeuble);
        setIdTailleMeuble(idTailleMeuble);
        setNomTailleMeuble(nomTailleMeuble);
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

    public Integer getIdTailleMeuble() {
        return idTailleMeuble;
    }

    public void setIdTailleMeuble(Integer idTailleMeuble) {
        this.idTailleMeuble = idTailleMeuble;
    }

    public String getNomTailleMeuble() {
        return nomTailleMeuble;
    }

    public void setNomTailleMeuble(String nomTailleMeuble) {
        this.nomTailleMeuble = nomTailleMeuble;
    }

    public void setvDetailEmployeMeubles(List<VDetailEmployeMeuble> vDetailEmployeMeubles) {
        this.vDetailEmployeMeubles = vDetailEmployeMeubles;
    }

    public List<VDetailEmployeMeuble> getvDetailEmployeMeubles() {
        return vDetailEmployeMeubles;
    }

    public void setvDetailFormuleMeubles(List<VDetailFormuleMeuble> vDetailFormuleMeubles) {
        this.vDetailFormuleMeubles = vDetailFormuleMeubles;
    }

    public List<VDetailFormuleMeuble> getvDetailFormuleMeubles() {
        return vDetailFormuleMeubles;
    }

    public static List<VFormuleMeuble> selectByIdMeuble(Connection connection, Integer idMeuble) throws SQLException {
        List<VFormuleMeuble> formules = new ArrayList<>();
        String query = "SELECT * FROM v_formule_meuble WHERE id_meuble = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idMeuble);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    Integer idTailleMeuble = resultSet.getInt("id_taille_meuble");
                    String nomTailleMeuble = resultSet.getString("nom_taille_meuble");
                    VFormuleMeuble formule = new VFormuleMeuble(id, idMeuble, idTailleMeuble, nomTailleMeuble);
                    formules.add(formule);
                }
            }
        }
        return formules;
    }
}
