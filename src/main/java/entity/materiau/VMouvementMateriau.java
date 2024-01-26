package entity.materiau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;

import com.genericdao.GenericDAO;
import com.genericdao.annotation.Column;
import com.genericdao.annotation.Table;

@Table(name = "v_mouvement_materiau")
public class VMouvementMateriau extends GenericDAO {

    @Column(name = "id")
    private Integer id;

    @Column(name = "date_mouvement")
    private LocalDateTime dateMouvement;

    @Column(name = "id_materiau")
    private Integer idMateriau;

    @Column(name = "quantite")
    private Double quantite;

    @Column(name = "prix_unitaire")
    private Double prixUnitaire;

    @Column(name = "type_mouvement")
    private Integer typeMouvement;

    @Column(name = "id_mouvement_mere")
    private Integer idMouvementMere;

    @Column(name = "description")
    private String description;

    @Column(name = "id_mouvement_meuble")
    private Integer idMouvementMeuble;

    @Column(name = "id_type_materiau")
    private Integer idTypeMateriau;

    @Column(name = "nom_materiau")
    private String nomMateriau;

    @Column(name = "nom_type_materiau")
    private String nomTypeMateriau;

    @Column(name = "prix_total")
    private Double prixTotal;

    public VMouvementMateriau() {

    }

    public VMouvementMateriau(Integer id, LocalDateTime dateMouvement, Integer idMateriau, Double quantite,
            Double prixUnitaire, Integer typeMouvement, Integer idMouvementMere, String description,
            Integer idMouvementMeuble, Integer idTypeMateriau, String nomMateriau, String nomTypeMateriau,
            Double prixTotal) {
        setId(id);
        setDateMouvement(dateMouvement);
        setIdMateriau(idMateriau);
        setQuantite(quantite);
        setPrixUnitaire(prixUnitaire);
        setTypeMouvement(typeMouvement);
        setIdMouvementMere(idMouvementMere);
        setDescription(description);
        setIdMouvementMeuble(idMouvementMeuble);
        setIdTypeMateriau(idTypeMateriau);
        setNomMateriau(nomMateriau);
        setNomTypeMateriau(nomTypeMateriau);
        setPrixTotal(prixTotal);

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

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setTypeMouvement(Integer typeMouvement) {
        this.typeMouvement = typeMouvement;
    }

    public Integer getTypeMouvement() {
        return typeMouvement;
    }

    public void setIdMouvementMere(Integer idMouvementMere) {
        this.idMouvementMere = idMouvementMere;
    }

    public Integer getIdMouvementMere() {
        return idMouvementMere;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setIdMouvementMeuble(Integer idMouvementMeuble) {
        this.idMouvementMeuble = idMouvementMeuble;
    }

    public Integer getIdMouvementMeuble() {
        return idMouvementMeuble;
    }

    public void setIdTypeMateriau(Integer idTypeMateriau) {
        this.idTypeMateriau = idTypeMateriau;
    }

    public Integer getIdTypeMateriau() {
        return idTypeMateriau;
    }

    public void setNomMateriau(String nomMateriau) {
        this.nomMateriau = nomMateriau;
    }

    public String getNomMateriau() {
        return nomMateriau;
    }

    public void setNomTypeMateriau(String nomTypeMateriau) {
        this.nomTypeMateriau = nomTypeMateriau;
    }

    public String getNomTypeMateriau() {
        return nomTypeMateriau;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public static List<VMouvementMateriau> selectByTypeMouvement(Connection connection, Integer typeMouvement,
            LocalDateTime dateDebut, LocalDateTime dateFin) throws Exception {
        String query = "select * from v_mouvement_materiau where type_mouvement= ? and date_mouvement>= ? and date_mouvement<= ? ";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, typeMouvement);
        statement.setObject(2, dateDebut);
        statement.setObject(3, dateFin);
        return VMouvementMateriau.selectMultipleByPreparedStatement(VMouvementMateriau.class, statement, connection);
    }

}