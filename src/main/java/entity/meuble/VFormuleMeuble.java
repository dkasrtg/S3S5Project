package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.genericdao.GenericDAO;
import com.genericdao.annotation.Column;
import com.genericdao.annotation.Table;

@Table(name = "v_formule_meuble")
public class VFormuleMeuble extends GenericDAO {

    @Column(name = "id")
    private Integer id;

    @Column(name = "id_meuble")
    private Integer idMeuble;

    @Column(name = "id_taille_meuble")
    private Integer idTailleMeuble;

    @Column(name = "nom_taille_meuble")
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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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

    public void setNomTailleMeuble(String nomTailleMeuble) {
        this.nomTailleMeuble = nomTailleMeuble;
    }

    public String getNomTailleMeuble() {
        return nomTailleMeuble;
    }

    public void setvDetailFormuleMeubles(List<VDetailFormuleMeuble> vDetailFormuleMeubles) {
        this.vDetailFormuleMeubles = vDetailFormuleMeubles;
    }

    public List<VDetailFormuleMeuble> getvDetailFormuleMeubles() {
        return vDetailFormuleMeubles;
    }

    public void setvDetailEmployeMeubles(List<VDetailEmployeMeuble> vDetailEmployeMeubles) {
        this.vDetailEmployeMeubles = vDetailEmployeMeubles;
    }

    public List<VDetailEmployeMeuble> getvDetailEmployeMeubles() {
        return vDetailEmployeMeubles;
    }

    public static List<VFormuleMeuble> selectByIdMeuble(Connection connection, Integer idMeuble) throws Exception {
        String query = "SELECT * FROM v_formule_meuble WHERE id_meuble = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idMeuble);
        return VFormuleMeuble.selectMultipleByPreparedStatement(VFormuleMeuble.class, statement, connection);
    }

}