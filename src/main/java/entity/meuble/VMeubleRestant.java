package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VMeubleRestant {
    private Integer id;
    private LocalDateTime dateMouvement;
    private Integer idFormuleMeuble;
    private Double prixUnitaire;
    private Double quantite;

    public VMeubleRestant(Integer id, LocalDateTime dateMouvement, Integer idFormuleMeuble, Double prixUnitaire,
            Double quantite) {
        setId(id);
        setDateMouvement(dateMouvement);
        setIdFormuleMeuble(idFormuleMeuble);
        setPrixUnitaire(prixUnitaire);
        setQuantite(quantite);
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

    public void setDateMouvement(LocalDateTime dateMouvement) {
        this.dateMouvement = dateMouvement;
    }

    public Integer getIdFormuleMeuble() {
        return idFormuleMeuble;
    }

    public void setIdFormuleMeuble(Integer idFormuleMeuble) {
        this.idFormuleMeuble = idFormuleMeuble;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public static List<VMeubleRestant> selectByIdFormuleMeuble(Connection connection, Integer idFormuleMeuble)
            throws Exception {
        List<VMeubleRestant> meublesRestants = new ArrayList<>();
        String query = "SELECT * FROM v_meuble_restant WHERE id_formule_meuble = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idFormuleMeuble);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            LocalDateTime dateMouvement = resultSet.getTimestamp("date_mouvement").toLocalDateTime();
            Double prixUnitaire = resultSet.getDouble("prix_unitaire");
            Double quantite = resultSet.getDouble("quantite");
            VMeubleRestant meubleRestant = new VMeubleRestant(id, dateMouvement, idFormuleMeuble, prixUnitaire,
                    quantite);
            meublesRestants.add(meubleRestant);
        }
        statement.close();
        resultSet.close();
        return meublesRestants;
    }
}
