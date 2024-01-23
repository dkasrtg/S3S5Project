package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;

import exception.DateAfterNowException;

public class MouvementMeuble {
    public static final int ENTREE = 1;
    public static final int SORTIE = -1;
    Integer id;
    LocalDateTime dateMouvement;
    Integer idFormuleMeuble;
    Double quantite;
    Double totalMateriaux;
    Double totalSalaires;
    Double prixTotal;
    Double prixUnitaire;
    Integer typeMouvement;
    Integer idMouvementMere;
    Integer idDetailVenteMeuble;
    String description;

    public MouvementMeuble(Integer id, LocalDateTime dateMouvement, Integer idFormuleMeuble, Double quantite,
            Double prixTotal,
            Double prixUnitaire, Integer typeMouvement, Integer idMouvementMere, Double totalMateriaux,
            Double totalSalaire, Integer idDetailVenteMeuble, String description) throws Exception {
        setId(id);
        setDateMouvement(dateMouvement);
        setidFormuleMeuble(idFormuleMeuble);
        setQuantite(quantite);
        setPrixTotal(prixTotal);
        setPrixUnitaire(prixUnitaire);
        setTypeMouvement(typeMouvement);
        setIdMouvementMere(idMouvementMere);
        setTotalMateriaux(totalMateriaux);
        setTotalSalaires(totalSalaire);
        setIdDetailVenteMeuble(idDetailVenteMeuble);
        setDescription(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setIdDetailVenteMeuble(Integer idDetailVenteMeuble) {
        this.idDetailVenteMeuble = idDetailVenteMeuble;
    }

    public Integer getIdDetailVenteMeuble() {
        return idDetailVenteMeuble;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateMouvement() {
        return dateMouvement;
    }

    public void setDateMouvement(LocalDateTime dateMouvement) throws Exception {
        if (dateMouvement.isAfter(LocalDateTime.now())) {
            throw new DateAfterNowException();
        }
        this.dateMouvement = dateMouvement;
    }

    public Integer getidFormuleMeuble() {
        return idFormuleMeuble;
    }

    public void setidFormuleMeuble(Integer idFormuleMeuble) {
        this.idFormuleMeuble = idFormuleMeuble;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Integer getTypeMouvement() {
        return typeMouvement;
    }

    public void setTypeMouvement(Integer typeMouvement) {
        this.typeMouvement = typeMouvement;
    }

    public Integer getIdMouvementMere() {
        return idMouvementMere;
    }

    public void setIdMouvementMere(Integer idMouvementMere) {
        this.idMouvementMere = idMouvementMere;
    }

    public void setTotalMateriaux(Double totalMateriaux) {
        this.totalMateriaux = totalMateriaux;
    }

    public void setTotalSalaires(Double totalSalaires) {
        this.totalSalaires = totalSalaires;
    }

    public Double getTotalMateriaux() {
        return totalMateriaux;
    }

    public Double getTotalSalaires() {
        return totalSalaires;
    }

    public void insert(Connection connection) throws Exception {
        String query = "INSERT INTO mouvement_meuble (date_mouvement, id_formule_meuble, quantite, prix_total,prix_unitaire, type_mouvement, id_mouvement_mere, total_materiaux, total_salaires, id_detail_vente_meuble,description) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setObject(1, getDateMouvement());
        statement.setInt(2, getidFormuleMeuble());
        statement.setDouble(3, getQuantite());
        statement.setDouble(4, getPrixTotal());
        statement.setDouble(5, getPrixUnitaire());
        statement.setInt(6, getTypeMouvement());
        statement.setInt(7, getIdMouvementMere());
        statement.setDouble(8, getTotalMateriaux());
        statement.setDouble(9, getTotalSalaires());
        statement.setInt(10, getIdDetailVenteMeuble());
        statement.setString(11, getDescription());
        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            setId(generatedKeys.getInt(1));
        }
        statement.close();
        generatedKeys.close();
    }

    public void update(Connection connection) throws Exception {
        String query = "UPDATE mouvement_meuble SET date_mouvement = ?, id_formule_meuble = ?, " +
                "quantite = ?, prix_total = ?, prix_unitaire = ?, type_mouvement = ?, id_mouvement_mere = ?, total_materiaux = ?, total_salaires = ?, id_detail_vente_meuble = ?, description = ? "
                +
                "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setObject(1, getDateMouvement());
        statement.setInt(2, getidFormuleMeuble());
        statement.setDouble(3, getQuantite());
        statement.setDouble(4, getPrixTotal());
        statement.setDouble(5, getPrixUnitaire());
        statement.setInt(6, getTypeMouvement());
        statement.setInt(7, getIdMouvementMere());
        statement.setDouble(8, getTotalMateriaux());
        statement.setDouble(9, getTotalSalaires());
        statement.setInt(10, getIdDetailVenteMeuble());
        statement.setString(11, getDescription());
        statement.setInt(12, getId());
        statement.executeUpdate();
        statement.close();
    }

    public static LocalDateTime getLastOutMouvementDate(Connection connection) throws Exception {
        String query = "SELECT MAX(date_mouvement) AS last_date FROM mouvement_meuble WHERE type_mouvement=-1";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            LocalDateTime result = resultSet.getObject("last_date", LocalDateTime.class);
            if (result != null) {
                return result;
            }
        }
        return LocalDateTime.of(1, 1, 1, 0, 0);
    }

}
