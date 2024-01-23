package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VMeublePossibleAVendre {
    private Integer id;
    private Integer idMeuble;
    private Integer idTailleMeuble;
    private String nomMeuble;
    private String nomTailleMeuble;
    private Double quantite;

    public VMeublePossibleAVendre(Integer id, Integer idMeuble, Integer idTailleMeuble, String nomMeuble,
            String nomTailleMeuble, Double quantite) {
        setId(id);
        setIdMeuble(idMeuble);
        setIdTailleMeuble(idTailleMeuble);
        setNomMeuble(nomMeuble);
        setNomTailleMeuble(nomTailleMeuble);
        setQuantite(quantite);
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

    public String getNomMeuble() {
        return nomMeuble;
    }

    public void setNomMeuble(String nomMeuble) {
        this.nomMeuble = nomMeuble;
    }

    public String getNomTailleMeuble() {
        return nomTailleMeuble;
    }

    public void setNomTailleMeuble(String nomTailleMeuble) {
        this.nomTailleMeuble = nomTailleMeuble;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public static List<VMeublePossibleAVendre> list(Connection connection) throws Exception {
        List<VMeublePossibleAVendre> meublesAVendre = new ArrayList<>();
        String query = "SELECT * FROM v_meuble_possible_a_vendre";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            Integer idMeuble = resultSet.getInt("id_meuble");
            Integer idTailleMeuble = resultSet.getInt("id_taille_meuble");
            String nomMeuble = resultSet.getString("nom_meuble");
            String nomTailleMeuble = resultSet.getString("nom_taille_meuble");
            Double quantite = resultSet.getDouble("quantite");

            VMeublePossibleAVendre meubleAVendre = new VMeublePossibleAVendre(
                    id, idMeuble, idTailleMeuble, nomMeuble, nomTailleMeuble, quantite);
            meublesAVendre.add(meubleAVendre);
        }
        statement.close();
        resultSet.close();
        return meublesAVendre;
    }
}
