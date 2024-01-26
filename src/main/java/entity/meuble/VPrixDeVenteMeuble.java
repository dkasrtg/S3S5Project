package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;

import com.genericdao.GenericDAO;
import com.genericdao.annotation.Column;
import com.genericdao.annotation.Table;

@Table(name = "v_prix_de_vente_meuble")
public class VPrixDeVenteMeuble extends GenericDAO {

    @Column(name = "id")
    private Integer id;

    @Column(name = "date_debut")
    private LocalDateTime dateDebut;

    @Column(name = "date_fin")
    private LocalDateTime dateFin;

    @Column(name = "valeur")
    private Double valeur;

    @Column(name = "id_formule_meuble")
    private Integer idFormuleMeuble;

    @Column(name = "id_meuble")
    private Integer idMeuble;

    @Column(name = "id_taille_meuble")
    private Integer idTailleMeuble;

    @Column(name = "nom_meuble")
    private String nomMeuble;

    @Column(name = "nom_taille_meuble")
    private String nomTailleMeuble;

    public VPrixDeVenteMeuble() {

    }

    public VPrixDeVenteMeuble(Integer id, LocalDateTime dateDebut, LocalDateTime dateFin, Double valeur,
            Integer idFormuleMeuble, Integer idMeuble, Integer idTailleMeuble, String nomMeuble,
            String nomTailleMeuble) {
        setId(id);
        setDateDebut(dateDebut);
        setDateFin(dateFin);
        setValeur(valeur);
        setIdFormuleMeuble(idFormuleMeuble);
        setIdMeuble(idMeuble);
        setIdTailleMeuble(idTailleMeuble);
        setNomMeuble(nomMeuble);
        setNomTailleMeuble(nomTailleMeuble);

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public Double getValeur() {
        return valeur;
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

    public static List<VPrixDeVenteMeuble> selectWhereDateInRange(Connection connection, LocalDateTime date)
            throws Exception {
        String query = "SELECT * FROM v_prix_de_vente_meuble WHERE date_debut <= ? AND date_fin > ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setObject(1, date);
        statement.setObject(2, date);
        return VPrixDeVenteMeuble.selectMultipleByPreparedStatement(VPrixDeVenteMeuble.class, statement, connection);
    }

}