package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ResteMeuble {
    private Integer id;
    private Integer idMeuble;
    private Integer idTailleMeuble;
    private String nomTailleMeuble;
    private String nomMeuble;
    private Double quantiteRestant;
    private Double prixTotal;
    private Double prixUnitaireMoyen;

    public ResteMeuble(Integer id, Integer idMeuble, Integer idTailleMeuble, String nomTailleMeuble, String nomMeuble,
            Double quantiteRestant, Double prixTotal, Double prixUnitaireMoyen) {
        setId(id);
        setIdMeuble(idMeuble);
        setIdTailleMeuble(idTailleMeuble);
        setNomTailleMeuble(nomTailleMeuble);
        setNomMeuble(nomMeuble);
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

    public Integer getIdMeuble() {
        return idMeuble;
    }

    public void setIdMeuble(Integer idMeuble) {
        this.idMeuble = idMeuble;
    }

    public Integer getIdTailleMeuble() {
        return idTailleMeuble;
    }

    public void setIdTailleMeuble(Integer idTailleMeuble) {
        this.idTailleMeuble = idTailleMeuble;
    }

    public String getNomTailleMeuble() {
        return nomTailleMeuble;
    }

    public void setNomTailleMeuble(String nomTailleMeuble) {
        this.nomTailleMeuble = nomTailleMeuble;
    }

    public String getNomMeuble() {
        return nomMeuble;
    }

    public void setNomMeuble(String nomMeuble) {
        this.nomMeuble = nomMeuble;
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

    public static List<ResteMeuble> selectByDate(Connection connection, LocalDateTime date) throws SQLException {
        List<ResteMeuble> resteMeubles = new ArrayList<>();
        String query = "select\r\n" + //
                "vfm.*,\r\n" + //
                "coalesce(q3.quantite_restant,0) as quantite_restant,\r\n" + //
                "coalesce(q3.prix_total,0) as prix_total,\r\n" + //
                "coalesce(q3.prix_unitaire_moyen,0) as prix_unitaire_moyen \r\n" + //
                "from v_formule_meuble vfm\r\n" + //
                "left join\r\n" + //
                "(select\r\n" + //
                "id_formule_meuble,qr as quantite_restant,ptr as prix_total,ptr/qr as prix_unitaire_moyen\r\n" + //
                "from\r\n" + //
                "(select\r\n" + //
                "id_formule_meuble,sum(qr) as qr,sum(ptr) as ptr\r\n" + //
                "from\r\n" + //
                "(select\r\n" + //
                "id,id_formule_meuble,qe-qs as qr,pte-pts as ptr\r\n" + //
                "from\r\n" + //
                "(select\r\n" + //
                "id,id_formule_meuble,qe,pte,sum(qs) as qs,sum(pts) as pts\r\n" + //
                "from\r\n" + //
                "(select\r\n" + //
                "mme.id,mme.id_formule_meuble,mme.quantite as qe ,mme.prix_total pte,\r\n" + //
                "coalesce(mms.quantite,0) as qs,coalesce(mms.prix_total,0) as pts\r\n" + //
                "from \r\n" + //
                "(select * from mouvement_meuble where type_mouvement=1 and date_mouvement <= ? ) as mme\r\n" + //
                "left join\r\n" + //
                "(select * from mouvement_meuble where type_mouvement=-1 and date_mouvement <= ? ) as mms\r\n" + //
                "on mme.id=mms.id_mouvement_mere) as q1\r\n" + //
                "group by id,id_formule_meuble,qe,pte ) as q2 ) as q3\r\n" + //
                "group by id_formule_meuble ) as q4) q3\r\n" + //
                "on q3.id_formule_meuble=vfm.id";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setObject(1, date);
        statement.setObject(2, date);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            Integer idTailleMeuble = resultSet.getInt("id_taille_meuble");
            Integer idMeuble = resultSet.getInt("id_meuble");
            String nomMeuble = resultSet.getString("nom_meuble");
            String nomTailleMeuble = resultSet.getString("nom_taille_meuble");
            Double quantiteRestant = resultSet.getDouble("quantite_restant");
            Double prixTotal = resultSet.getDouble("prix_total");
            Double prixUnitaireMoyen = resultSet.getDouble("prix_unitaire_moyen");
            ResteMeuble resteMeuble = new ResteMeuble(id, idMeuble, idTailleMeuble, nomTailleMeuble, nomMeuble, quantiteRestant, prixTotal, prixUnitaireMoyen);
            resteMeubles.add(resteMeuble);
        }
        statement.close();
        resultSet.close();
        return resteMeubles;
    }

}
