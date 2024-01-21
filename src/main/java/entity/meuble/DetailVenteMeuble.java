package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DetailVenteMeuble {
    private Integer id;
    private Integer idVenteMeuble;
    private Integer idFormuleMeuble;
    private Double quantite;
    private Double prixUnitaire;
    private Double remise;
    private Double prixUnitaireAvecRemise;
    private Double prixTotal;

    public DetailVenteMeuble(Integer id, Integer idVenteMeuble, Integer idFormuleMeuble, Double quantite,
            Double prixUnitaire, Double remise, Double prixUnitaireAvecRemise, Double prixTotal) {
        setId(id);
        setIdVenteMeuble(idVenteMeuble);
        setIdFormuleMeuble(idFormuleMeuble);
        setQuantite(quantite);
        setPrixUnitaire(prixUnitaire);
        setRemise(remise);
        setPrixUnitaireAvecRemise(prixUnitaireAvecRemise);
        setPrixTotal(prixTotal);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdVenteMeuble() {
        return idVenteMeuble;
    }

    public void setIdVenteMeuble(Integer idVenteMeuble) {
        this.idVenteMeuble = idVenteMeuble;
    }

    public Integer getIdFormuleMeuble() {
        return idFormuleMeuble;
    }

    public void setIdFormuleMeuble(Integer idFormuleMeuble) {
        this.idFormuleMeuble = idFormuleMeuble;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Double getRemise() {
        return remise;
    }

    public void setRemise(Double remise) {
        this.remise = remise;
    }

    public Double getPrixUnitaireAvecRemise() {
        return prixUnitaireAvecRemise;
    }

    public void setPrixUnitaireAvecRemise(Double prixUnitaireAvecRemise) {
        this.prixUnitaireAvecRemise = prixUnitaireAvecRemise;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO detail_vente_meuble (id_vente_meuble, id_formule_meuble, quantite, prix_unitaire, remise, prix_unitaire_avec_remise, prix_total) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, getIdVenteMeuble());
            statement.setInt(2, getIdFormuleMeuble());
            statement.setDouble(3, getQuantite());
            statement.setDouble(4, getPrixUnitaire());
            statement.setDouble(5, getRemise());
            statement.setDouble(6, getPrixUnitaireAvecRemise());
            statement.setDouble(7, getPrixTotal());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    setId(generatedKeys.getInt(1));
                }
            }
        }
    }
}
