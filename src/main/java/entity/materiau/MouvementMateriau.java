package entity.materiau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import exception.DateAfterNowException;
import exception.QuantiteNegatifZeroException;

public class MouvementMateriau {
    public static final int ENTREE = 1;
    public static final int SORTIE = -1;
    Integer id;
    LocalDateTime dateMouvement;
    Integer idMateriau;
    Double quantite;
    Double prixUnitaire;
    Integer typeMouvement;
    Integer idMouvementMere;
    String description;
    Integer idMouvementMeuble;

    public MouvementMateriau() {
    }

    public MouvementMateriau(Integer id, LocalDateTime dateMouvement, Integer idMateriau, Double quantite,
            Double prixUnitaire, Integer typeMouvement, Integer idMouvementMere, String description,
            Integer idMouvementMeuble) throws Exception {
        setId(id);
        setDateMouvement(dateMouvement);
        setIdMateriau(idMateriau);
        setQuantite(quantite);
        setPrixUnitaire(prixUnitaire);
        setTypeMouvement(typeMouvement);
        setIdMouvementMere(idMouvementMere);
        setDescription(description);
        setIdMouvementMeuble(idMouvementMeuble);
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

    public Integer getIdMateriau() {
        return idMateriau;
    }

    public void setIdMateriau(Integer idMateriau) {
        this.idMateriau = idMateriau;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) throws Exception {
        if (quantite <= 0) {
            throw new QuantiteNegatifZeroException();
        }
        this.quantite = quantite;
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

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO mouvement_materiau (date_mouvement, id_materiau, quantite, prix_unitaire, type_mouvement, id_mouvement_mere, description, id_mouvement_meuble) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setObject(1, getDateMouvement());
            statement.setInt(2, getIdMateriau());
            statement.setDouble(3, getQuantite());
            statement.setDouble(4, getPrixUnitaire());
            statement.setInt(5, getTypeMouvement());
            statement.setInt(6, getIdMouvementMere());
            statement.setString(7, getDescription());
            statement.setInt(8, getIdMouvementMeuble());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                setId(generatedKeys.getInt(1));
            }
        }
    }

    public static LocalDateTime getLastOutMouvementDate(Connection connection) throws SQLException {
        String query = "SELECT MAX(date_mouvement) AS last_date FROM mouvement_materiau WHERE type_mouvement=-1";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    LocalDateTime result = resultSet.getObject("last_date", LocalDateTime.class);
                    if (result != null) {
                        return result;
                    }
                }
            }
        }
        return LocalDateTime.of(1, 1, 1, 0, 0);
    }

}