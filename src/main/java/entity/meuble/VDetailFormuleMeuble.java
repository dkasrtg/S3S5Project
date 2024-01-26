package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.genericdao.GenericDAO;
import com.genericdao.annotation.Column;
import com.genericdao.annotation.Table;

@Table(name = "v_detail_formule_meuble")
public class VDetailFormuleMeuble extends GenericDAO {

    @Column(name = "id")
    private Integer id;

    @Column(name = "id_formule_meuble")
    private Integer idFormuleMeuble;

    @Column(name = "id_materiau")
    private Integer idMateriau;

    @Column(name = "quantite")
    private Double quantite;

    @Column(name = "nom_materiau")
    private String nomMateriau;

    public VDetailFormuleMeuble() {

    }

    public VDetailFormuleMeuble(Integer id, Integer idFormuleMeuble, Integer idMateriau, Double quantite,
            String nomMateriau) {
        setId(id);
        setIdFormuleMeuble(idFormuleMeuble);
        setIdMateriau(idMateriau);
        setQuantite(quantite);
        setNomMateriau(nomMateriau);

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setIdFormuleMeuble(Integer idFormuleMeuble) {
        this.idFormuleMeuble = idFormuleMeuble;
    }

    public Integer getIdFormuleMeuble() {
        return idFormuleMeuble;
    }

    public void setIdMateriau(Integer idMateriau) {
        this.idMateriau = idMateriau;
    }

    public Integer getIdMateriau() {
        return idMateriau;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setNomMateriau(String nomMateriau) {
        this.nomMateriau = nomMateriau;
    }

    public String getNomMateriau() {
        return nomMateriau;
    }

    public static List<VDetailFormuleMeuble> selectByIdFormuleMeuble(Connection connection, Integer idFormuleMeuble)
            throws Exception {
        String query = "SELECT * FROM v_detail_formule_meuble WHERE id_formule_meuble = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idFormuleMeuble);
        return VDetailFormuleMeuble.selectMultipleByPreparedStatement(VDetailFormuleMeuble.class, statement,
                connection);
    }

}