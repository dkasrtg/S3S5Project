package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;

import com.genericdao.GenericDAO;
import com.genericdao.annotation.Column;
import com.genericdao.annotation.Id;
import com.genericdao.annotation.Table;

@Table(name = "v_mouvement_meuble")
public class VMouvementMeuble extends GenericDAO {

    @Id(autoGenerated = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date_mouvement")
    private LocalDateTime dateMouvement;

    @Column(name = "id_formule_meuble")
    private Integer idFormuleMeuble;

    @Column(name = "quantite")
    private Double quantite;

    @Column(name = "type_mouvement")
    private Integer typeMouvement;

    @Column(name = "id_mouvement_mere")
    private Integer idMouvementMere;

    @Column(name = "total_materiaux")
    private Double totalMateriaux;

    @Column(name = "total_salaires")
    private Double totalSalaires;

    @Column(name = "prix_total")
    private Double prixTotal;

    @Column(name = "prix_unitaire")
    private Double prixUnitaire;

    @Column(name = "id_detail_vente_meuble")
    private Integer idDetailVenteMeuble;

    @Column(name = "description")
    private String description;

    @Column(name = "id_taille_meuble")
    private Integer idTailleMeuble;

    @Column(name = "id_meuble")
    private Integer idMeuble;

    @Column(name = "nom_taille_meuble")
    private String nomTailleMeuble;

    @Column(name = "nom_meuble")
    private String nomMeuble;

    @Column(name = "id_style_meuble")
    private Integer idStyleMeuble;

    @Column(name = "id_categorie_meuble")
    private Integer idCategorieMeuble;

    @Column(name = "nom_style_meuble")
    private String nomStyleMeuble;

    @Column(name = "nom_categorie_meuble")
    private String nomCategorieMeuble;

    public VMouvementMeuble() {

    }

    public VMouvementMeuble(Integer id, LocalDateTime dateMouvement, Integer idFormuleMeuble, Double quantite,
            Integer typeMouvement, Integer idMouvementMere, Double totalMateriaux, Double totalSalaires,
            Double prixTotal, Double prixUnitaire, Integer idDetailVenteMeuble, String description,
            Integer idTailleMeuble, Integer idMeuble, String nomTailleMeuble, String nomMeuble, Integer idStyleMeuble,
            Integer idCategorieMeuble, String nomStyleMeuble, String nomCategorieMeuble) {
        setId(id);
        setDateMouvement(dateMouvement);
        setIdFormuleMeuble(idFormuleMeuble);
        setQuantite(quantite);
        setTypeMouvement(typeMouvement);
        setIdMouvementMere(idMouvementMere);
        setTotalMateriaux(totalMateriaux);
        setTotalSalaires(totalSalaires);
        setPrixTotal(prixTotal);
        setPrixUnitaire(prixUnitaire);
        setIdDetailVenteMeuble(idDetailVenteMeuble);
        setDescription(description);
        setIdTailleMeuble(idTailleMeuble);
        setIdMeuble(idMeuble);
        setNomTailleMeuble(nomTailleMeuble);
        setNomMeuble(nomMeuble);
        setIdStyleMeuble(idStyleMeuble);
        setIdCategorieMeuble(idCategorieMeuble);
        setNomStyleMeuble(nomStyleMeuble);
        setNomCategorieMeuble(nomCategorieMeuble);

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

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getQuantite() {
        return quantite;
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

    public void setTotalMateriaux(Double totalMateriaux) {
        this.totalMateriaux = totalMateriaux;
    }

    public Double getTotalMateriaux() {
        return totalMateriaux;
    }

    public void setTotalSalaires(Double totalSalaires) {
        this.totalSalaires = totalSalaires;
    }

    public Double getTotalSalaires() {
        return totalSalaires;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setIdDetailVenteMeuble(Integer idDetailVenteMeuble) {
        this.idDetailVenteMeuble = idDetailVenteMeuble;
    }

    public Integer getIdDetailVenteMeuble() {
        return idDetailVenteMeuble;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setIdTailleMeuble(Integer idTailleMeuble) {
        this.idTailleMeuble = idTailleMeuble;
    }

    public Integer getIdTailleMeuble() {
        return idTailleMeuble;
    }

    public void setIdMeuble(Integer idMeuble) {
        this.idMeuble = idMeuble;
    }

    public Integer getIdMeuble() {
        return idMeuble;
    }

    public void setNomTailleMeuble(String nomTailleMeuble) {
        this.nomTailleMeuble = nomTailleMeuble;
    }

    public String getNomTailleMeuble() {
        return nomTailleMeuble;
    }

    public void setNomMeuble(String nomMeuble) {
        this.nomMeuble = nomMeuble;
    }

    public String getNomMeuble() {
        return nomMeuble;
    }

    public void setIdStyleMeuble(Integer idStyleMeuble) {
        this.idStyleMeuble = idStyleMeuble;
    }

    public Integer getIdStyleMeuble() {
        return idStyleMeuble;
    }

    public void setIdCategorieMeuble(Integer idCategorieMeuble) {
        this.idCategorieMeuble = idCategorieMeuble;
    }

    public Integer getIdCategorieMeuble() {
        return idCategorieMeuble;
    }

    public void setNomStyleMeuble(String nomStyleMeuble) {
        this.nomStyleMeuble = nomStyleMeuble;
    }

    public String getNomStyleMeuble() {
        return nomStyleMeuble;
    }

    public void setNomCategorieMeuble(String nomCategorieMeuble) {
        this.nomCategorieMeuble = nomCategorieMeuble;
    }

    public String getNomCategorieMeuble() {
        return nomCategorieMeuble;
    }

    public static List<VMouvementMeuble> selectByTypeMouvement(Connection connection, Integer typeMouvement,
            LocalDateTime dateDebut, LocalDateTime dateFin) throws Exception {
        String query = "SELECT * FROM v_mouvement_meuble WHERE type_mouvement = ? AND date_mouvement >= ? AND date_mouvement <= ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, typeMouvement);
        statement.setObject(2, dateDebut);
        statement.setObject(3, dateFin);
        return VMouvementMeuble.selectMultipleByPreparedStatement(VMouvementMeuble.class, statement, connection);
    }

}