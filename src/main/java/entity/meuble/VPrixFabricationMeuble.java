package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.genericdao.GenericDAO;
import com.genericdao.annotation.Column;
import com.genericdao.annotation.Table;

@Table(name = "v_prix_fabrication_meuble")
public class VPrixFabricationMeuble extends GenericDAO {

    @Column(name = "id_formule_meuble")
    private Integer idFormuleMeuble;

    @Column(name = "id_meuble")
    private Integer idMeuble;

    @Column(name = "id_taille_meuble")
    private Integer idTailleMeuble;

    @Column(name = "prix_fabrication")
    private Double prixFabrication;

    @Column(name = "nom_meuble")
    private String nomMeuble;

    @Column(name = "nom_taille_meuble")
    private String nomTailleMeuble;

    public VPrixFabricationMeuble() {

    }

    public VPrixFabricationMeuble(Integer idFormuleMeuble, Integer idMeuble, Integer idTailleMeuble,
            Double prixFabrication, String nomMeuble, String nomTailleMeuble) {
        setIdFormuleMeuble(idFormuleMeuble);
        setIdMeuble(idMeuble);
        setIdTailleMeuble(idTailleMeuble);
        setPrixFabrication(prixFabrication);
        setNomMeuble(nomMeuble);
        setNomTailleMeuble(nomTailleMeuble);

    }

    public void setIdFormuleMeuble(Integer idFormuleMeuble) {
        this.idFormuleMeuble = idFormuleMeuble;
    }

    public Integer getIdFormuleMeuble() {
        return idFormuleMeuble;
    }

    public void setIdMeuble(Integer idMeuble) {
        this.idMeuble = idMeuble;
    }

    public Integer getIdMeuble() {
        return idMeuble;
    }

    public void setIdTailleMeuble(Integer idTailleMeuble) {
        this.idTailleMeuble = idTailleMeuble;
    }

    public Integer getIdTailleMeuble() {
        return idTailleMeuble;
    }

    public void setPrixFabrication(Double prixFabrication) {
        this.prixFabrication = prixFabrication;
    }

    public Double getPrixFabrication() {
        return prixFabrication;
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

    public static List<VPrixFabricationMeuble> selectWherePrixFabricationBetween(Connection connection, double min,
            double max)
            throws Exception {
        String query = "SELECT * FROM v_prix_fabrication_meuble WHERE prix_fabrication BETWEEN ? AND ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setDouble(1, min);
        statement.setDouble(2, max);
        return VPrixFabricationMeuble.selectMultipleByPreparedStatement(VPrixFabricationMeuble.class, statement,
                connection);
    }

}