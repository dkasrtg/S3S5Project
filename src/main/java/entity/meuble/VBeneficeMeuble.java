package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VBeneficeMeuble {
    Integer idMeuble;
    Integer idTailleMeuble;
    String nomMeuble;
    String nomTailleMeuble;
    Double prixDeVente;
    Double totalMateriaux;
    Double totalSalaires;
    Double prixDeRevient;
    Double benefice;
    Integer idFormuleMeuble;

    public VBeneficeMeuble(Integer idMeuble, Integer idTailleMeuble, String nomMeuble, String nomTailleMeuble,
            Double prixDeVente, Double totalMateriaux,
            Double totalSalaires, Double prixDeRevient, Double benefice, Integer idFormuleMeuble) {
        setIdMeuble(idMeuble);
        setIdTailleMeuble(idTailleMeuble);
        setPrixDeRevient(prixDeRevient);
        setTotalMateriaux(totalMateriaux);
        setTotalSalaires(totalSalaires);
        setPrixDeVente(prixDeVente);
        setBenefice(benefice);
        setNomMeuble(nomMeuble);
        setNomTailleMeuble(nomTailleMeuble);
        setIdFormuleMeuble(idFormuleMeuble);
    }

    public void setIdFormuleMeuble(Integer idFormuleMeuble) {
        this.idFormuleMeuble = idFormuleMeuble;
    }

    public Integer getIdFormuleMeuble() {
        return idFormuleMeuble;
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

    public Double getPrixDeVente() {
        return prixDeVente;
    }

    public void setPrixDeVente(Double prixDeVente) {
        this.prixDeVente = prixDeVente;
    }

    public Double getTotalMateriaux() {
        return totalMateriaux;
    }

    public void setTotalMateriaux(Double totalMateriaux) {
        this.totalMateriaux = totalMateriaux;
    }

    public Double getTotalSalaires() {
        return totalSalaires;
    }

    public void setTotalSalaires(Double totalSalaires) {
        this.totalSalaires = totalSalaires;
    }

    public Double getPrixDeRevient() {
        return prixDeRevient;
    }

    public void setPrixDeRevient(Double prixDeRevient) {
        this.prixDeRevient = prixDeRevient;
    }

    public Double getBenefice() {
        return benefice;
    }

    public void setBenefice(Double benefice) {
        this.benefice = benefice;
    }

    public void setNomMeuble(String nomMeuble) {
        this.nomMeuble = nomMeuble;
    }

    public String getNomMeuble() {
        return nomMeuble;
    }

    public void setNomTailleMeuble(String nomTailleMeuble) {
        this.nomTailleMeuble = nomTailleMeuble;
    }

    public String getNomTailleMeuble() {
        return nomTailleMeuble;
    }

    public static List<VBeneficeMeuble> selectByBeneficeRange(Connection connection, Double minBenefice,
            Double maxBenefice) throws Exception {
        List<VBeneficeMeuble> benefices = new ArrayList<>();
        String query = "SELECT * FROM v_benefice_meuble WHERE benefice BETWEEN ? AND ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setDouble(1, minBenefice);
        statement.setDouble(2, maxBenefice);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Integer idMeuble = resultSet.getInt("id_meuble");
            Integer idTailleMeuble = resultSet.getInt("id_taille_meuble");
            String nomMeuble = resultSet.getString("nom_meuble");
            String nomTailleMeuble = resultSet.getString("nom_taille_meuble");
            Double prixDeVente = resultSet.getDouble("prix_de_vente");
            Double totalMateriaux = resultSet.getDouble("total_materiaux");
            Double totalSalaires = resultSet.getDouble("total_salaires");
            Double prixDeRevient = resultSet.getDouble("prix_de_revient");
            Double benefice = resultSet.getDouble("benefice");
            Integer idFormuleMeuble = resultSet.getInt("id_formule_meuble");
            VBeneficeMeuble beneficeMeuble = new VBeneficeMeuble(idMeuble, idTailleMeuble, nomMeuble,
                    nomTailleMeuble,
                    prixDeVente, totalMateriaux, totalSalaires, prixDeRevient, benefice, idFormuleMeuble);
            benefices.add(beneficeMeuble);
        }
        statement.close();
        resultSet.close();
        return benefices;
    }

    public static List<VBeneficeMeuble> list(Connection connection) throws Exception {
        List<VBeneficeMeuble> benefices = new ArrayList<>();
        String query = "SELECT * FROM v_benefice_meuble order by benefice desc";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Integer idMeuble = resultSet.getInt("id_meuble");
            Integer idTailleMeuble = resultSet.getInt("id_taille_meuble");
            String nomMeuble = resultSet.getString("nom_meuble");
            String nomTailleMeuble = resultSet.getString("nom_taille_meuble");
            Double prixDeVente = resultSet.getDouble("prix_de_vente");
            Double totalMateriaux = resultSet.getDouble("total_materiaux");
            Double totalSalaires = resultSet.getDouble("total_salaires");
            Double prixDeRevient = resultSet.getDouble("prix_de_revient");
            Double benefice = resultSet.getDouble("benefice");
            Integer idFormuleMeuble = resultSet.getInt("id_formule_meuble");
            VBeneficeMeuble beneficeMeuble = new VBeneficeMeuble(idMeuble, idTailleMeuble, nomMeuble,
                    nomTailleMeuble,
                    prixDeVente, totalMateriaux, totalSalaires, prixDeRevient, benefice, idFormuleMeuble);
            benefices.add(beneficeMeuble);
        }
        statement.close();
        resultSet.close();
        return benefices;
    }
}
