package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VPrixFabricationMeuble {
    private Integer idFormuleMeuble;
    private Integer idMeuble;
    private Integer idTailleMeuble;
    private Double prixFabrication;
    private String nomMeuble;
    private String nomTailleMeuble;

    public VPrixFabricationMeuble() {
    }

    public VPrixFabricationMeuble(Integer idFormuleMeuble, Integer idMeuble, Integer idTailleMeuble,
            Double prixFabrication,
            String nomMeuble, String nomTailleMeuble) {
        setIdFormuleMeuble(idFormuleMeuble);
        setIdMeuble(idMeuble);
        setIdTailleMeuble(idTailleMeuble);
        setPrixFabrication(prixFabrication);
        setNomMeuble(nomMeuble);
        setNomTailleMeuble(nomTailleMeuble);
    }

    public Integer getIdFormuleMeuble() {
        return idFormuleMeuble;
    }

    public void setIdFormuleMeuble(Integer idFormuleMeuble) {
        this.idFormuleMeuble = idFormuleMeuble;
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

    public Double getPrixFabrication() {
        return prixFabrication;
    }

    public void setPrixFabrication(Double prixFabrication) {
        this.prixFabrication = prixFabrication;
    }

    public String getNomMeuble() {
        return nomMeuble;
    }

    public void setNomMeuble(String nomMeuble) {
        this.nomMeuble = nomMeuble;
    }

    public String getNomTailleMeuble() {
        return nomTailleMeuble;
    }

    public void setNomTailleMeuble(String nomTailleMeuble) {
        this.nomTailleMeuble = nomTailleMeuble;
    }

    public static List<VPrixFabricationMeuble> selectWherePrixFabricationBetween(Connection connection, double min,
            double max)
            throws Exception {
        List<VPrixFabricationMeuble> result = new ArrayList<>();
        String query = "SELECT * FROM v_prix_fabrication_meuble WHERE prix_fabrication BETWEEN ? AND ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setDouble(1, min);
        statement.setDouble(2, max);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Integer idFormuleMeuble = resultSet.getInt("id_formule_meuble");
            Integer idMeuble = resultSet.getInt("id_meuble");
            Integer idTailleMeuble = resultSet.getInt("id_taille_meuble");
            Double prixFabrication = resultSet.getDouble("prix_fabrication");
            String nomMeuble = resultSet.getString("nom_meuble");
            String nomTailleMeuble = resultSet.getString("nom_taille_meuble");

            VPrixFabricationMeuble prixFabricationMeuble = new VPrixFabricationMeuble(
                    idFormuleMeuble, idMeuble, idTailleMeuble, prixFabrication, nomMeuble, nomTailleMeuble);
            result.add(prixFabricationMeuble);
        }
        statement.close();
        resultSet.close();
        return result;
    }
}
