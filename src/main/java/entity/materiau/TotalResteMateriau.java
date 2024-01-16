package entity.materiau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TotalResteMateriau {
    Integer id;
    String nom;
    Integer idTypeMateriau;
    String nomTypeMateriau;
    Double quantiteRestant;
    Double prixTotal;
    Double prixUnitaireMoyen;

    public TotalResteMateriau(Integer id, String nom, Integer idTypeMateriau, String nomTypeMateriau,
            Double quantiteRestant, Double prixTotal, Double prixUnitaireMoyen) {
        setId(id);
        setNom(nom);
        setIdTypeMateriau(idTypeMateriau);
        setNomTypeMateriau(nomTypeMateriau);
        setQuantiteRestant(quantiteRestant);
        setPrixTotal(prixTotal);
        setPrixUnitaireMoyen(prixUnitaireMoyen);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getIdTypeMateriau() {
        return idTypeMateriau;
    }

    public void setIdTypeMateriau(Integer idTypeMateriau) {
        this.idTypeMateriau = idTypeMateriau;
    }

    public String getNomTypeMateriau() {
        return nomTypeMateriau;
    }

    public void setNomTypeMateriau(String nomTypeMateriau) {
        this.nomTypeMateriau = nomTypeMateriau;
    }

    public Double getQuantiteRestant() {
        return quantiteRestant;
    }

    public void setQuantiteRestant(Double quantiteRestant) {
        this.quantiteRestant = quantiteRestant;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Double getPrixUnitaireMoyen() {
        return prixUnitaireMoyen;
    }

    public void setPrixUnitaireMoyen(Double prixUnitaireMoyen) {
        this.prixUnitaireMoyen = prixUnitaireMoyen;
    }

    public static List<TotalResteMateriau> list(Connection connection,LocalDateTime date) throws SQLException {
        List<TotalResteMateriau> totalResteMateriaus = new ArrayList<>();
        String query = "select v_materiau.id,v_materiau.nom,v_materiau.id_type_materiau,v_materiau.nom_type_materiau,\r\n" + //
                "coalesce(q7.quantite_restant,0) as quantite_restant,\r\n" + //
                "coalesce(q7.prix_total,0) as prix_total,\r\n" + //
                "coalesce(q7.prix_unitaire_moyen,0) as prix_unitaire_moyen\r\n" + //
                "from v_materiau\r\n" + //
                "left join \r\n" + //
                "(select id_materiau,q_r as quantite_restant,p_r as prix_total,p_r/q_r as prix_unitaire_moyen from (\r\n" + //
                "select id_materiau,sum(q_r) as q_r,sum(p_r) as p_r from (\r\n" + //
                "select id,id_materiau,q_r,q_r*prix_unitaire as p_r from (\r\n" + //
                "select id,id_materiau,prix_unitaire,q_e-q_s as q_r from (\r\n" + //
                "select id,id_materiau,prix_unitaire,q_e,sum(q_s) as q_s from (\r\n" + //
                "select id,id_materiau,prix_unitaire,q_e,coalesce(q_s,0) as q_s from (\r\n" + //
                "select mm_e.id,mm_e.id_materiau,mm_e.quantite as q_e ,mm_e.prix_unitaire, mm_s.quantite as q_s from\r\n" + //
                "(select * from mouvement_materiau where date_mouvement<= ? and type_mouvement=1) as mm_e\r\n" + //
                "left join \r\n" + //
                "(select * from mouvement_materiau where date_mouvement<= ? and type_mouvement=-1) as mm_s\r\n" + //
                "on mm_e.id = mm_s.id_mouvement_mere)\r\n" + //
                "as q1)\r\n" + //
                "as q2\r\n" + //
                "group by id,id_materiau,prix_unitaire,q_e)\r\n" + //
                "as q3)\r\n" + //
                "as q4)\r\n" + //
                "as q5\r\n" + //
                "group by id_materiau)\r\n" + //
                "as q6)\r\n" + //
                "as q7\r\n" + //
                "on v_materiau.id=q7.id_materiau\r\n" + //
                "";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setObject(1, date);
        statement.setObject(2, date);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            Integer idTypeMateriau = resultSet.getInt("id_type_materiau");
            String nomTypeMateriau = resultSet.getString("nom_type_materiau");
            Double quantiteRestant = resultSet.getDouble("quantite_restant");
            Double prixTotal = resultSet.getDouble("prix_total");
            Double prixUnitaireMoyen = resultSet.getDouble("prix_unitaire_moyen");

            TotalResteMateriau totalResteMateriau = new TotalResteMateriau(
                    id, nom, idTypeMateriau, nomTypeMateriau, quantiteRestant, prixTotal, prixUnitaireMoyen);

            totalResteMateriaus.add(totalResteMateriau);
        }
        return totalResteMateriaus;
    }

}
