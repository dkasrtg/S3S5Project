package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public MouvementMeuble(Integer id, LocalDateTime dateMouvement, Integer idFormuleMeuble, Double quantite,
            Double prixTotal,
            Double prixUnitaire, Integer typeMouvement, Integer idMouvementMere, Double totalMateriaux,
            Double totalSalaire) throws Exception {
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

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO mouvement_meuble (date_mouvement, id_formule_meuble, quantite, prix_total,prix_unitaire, type_mouvement, id_mouvement_mere, total_materiaux, total_salaires) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setObject(1, getDateMouvement());
            statement.setInt(2, getidFormuleMeuble());
            statement.setDouble(3, getQuantite());
            statement.setDouble(4, getPrixTotal());
            statement.setDouble(5, getPrixUnitaire());
            statement.setInt(6, getTypeMouvement());
            statement.setInt(7, getIdMouvementMere());
            statement.setDouble(8, getTotalMateriaux());
            statement.setDouble(9, getTotalSalaires());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                setId(generatedKeys.getInt(1));
            }
        }
    }

    public void update(Connection connection) throws SQLException {
        String query = "UPDATE mouvement_meuble SET date_mouvement = ?, id_formule_meuble = ?, " +
                "quantite = ?, prix_total = ?, prix_unitaire = ?, type_mouvement = ?, id_mouvement_mere = ?, total_materiaux = ?, total_salaires = ? "
                +
                "WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, getDateMouvement());
            statement.setInt(2, getidFormuleMeuble());
            statement.setDouble(3, getQuantite());
            statement.setDouble(4, getPrixTotal());
            statement.setDouble(5, getPrixUnitaire());
            statement.setInt(6, getTypeMouvement());
            statement.setInt(7, getIdMouvementMere());
            statement.setDouble(8, getTotalMateriaux());
            statement.setDouble(9, getTotalSalaires());
            statement.setInt(10, getId());
            statement.executeUpdate();
        }
    }

}
