package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;

import com.genericdao.GenericDAO;
import com.genericdao.annotation.Column;
import com.genericdao.annotation.Table;

@Table(name = "v_meuble_restant")
public class VMeubleRestant extends GenericDAO {

    @Column(name = "id")
    private Integer id;

    @Column(name = "date_mouvement")
    private LocalDateTime dateMouvement;

    @Column(name = "id_formule_meuble")
    private Integer idFormuleMeuble;

    @Column(name = "prix_unitaire")
    private Double prixUnitaire;

    @Column(name = "quantite")
    private Double quantite;

    public VMeubleRestant() {

    }

    public VMeubleRestant(Integer id, LocalDateTime dateMouvement, Integer idFormuleMeuble, Double prixUnitaire,
            Double quantite) {
        setId(id);
        setDateMouvement(dateMouvement);
        setIdFormuleMeuble(idFormuleMeuble);
        setPrixUnitaire(prixUnitaire);
        setQuantite(quantite);

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setDateMouvement(LocalDateTime dateMouvement) {
        this.dateMouvement = dateMouvement;
    }

    public LocalDateTime getDateMouvement() {
        return dateMouvement;
    }

    public void setIdFormuleMeuble(Integer idFormuleMeuble) {
        this.idFormuleMeuble = idFormuleMeuble;
    }

    public Integer getIdFormuleMeuble() {
        return idFormuleMeuble;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getQuantite() {
        return quantite;
    }

    public static List<VMeubleRestant> selectByIdFormuleMeuble(Connection connection, Integer idFormuleMeuble)
            throws Exception {
        String query = "SELECT * FROM v_meuble_restant WHERE id_formule_meuble = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idFormuleMeuble);
        return VMeubleRestant.selectMultipleByPreparedStatement(VMeubleRestant.class, statement, connection);
    }

}