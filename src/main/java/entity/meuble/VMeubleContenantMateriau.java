package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VMeubleContenantMateriau {
    private Integer id;
    private Integer idMeuble;
    private Integer idTailleMeuble;
    private String nomMeuble;
    private Integer idStyleMeuble;
    private Integer idCategorieMeuble;
    private String nomStyleMeuble;
    private String nomCategorieMeuble;
    private Double quantite;
    private Integer idMateriau;
    private String nomTailleMeuble;

    public VMeubleContenantMateriau() {
    }

    public VMeubleContenantMateriau(Integer id, Integer idMeuble, Integer idTailleMeuble, String nomMeuble,
            Integer idStyleMeuble, Integer idCategorieMeuble, String nomStyleMeuble,
            String nomCategorieMeuble, Double quantite, Integer idMateriau,
            String nomTailleMeuble) {
        setId(id);
        setIdMeuble(idMeuble);
        setIdMateriau(idMateriau);
        setIdTailleMeuble(idTailleMeuble);
        setNomMeuble(nomMeuble);
        setNomCategorieMeuble(nomCategorieMeuble);
        setNomStyleMeuble(nomStyleMeuble);
        setQuantite(quantite);
        setIdStyleMeuble(idStyleMeuble);
        setIdCategorieMeuble(idCategorieMeuble);
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

    public Integer getIdStyleMeuble() {
        return idStyleMeuble;
    }

    public void setIdStyleMeuble(Integer idStyleMeuble) {
        this.idStyleMeuble = idStyleMeuble;
    }

    public Integer getIdCategorieMeuble() {
        return idCategorieMeuble;
    }

    public void setIdCategorieMeuble(Integer idCategorieMeuble) {
        this.idCategorieMeuble = idCategorieMeuble;
    }

    public String getNomStyleMeuble() {
        return nomStyleMeuble;
    }

    public void setNomStyleMeuble(String nomStyleMeuble) {
        this.nomStyleMeuble = nomStyleMeuble;
    }

    public String getNomCategorieMeuble() {
        return nomCategorieMeuble;
    }

    public void setNomCategorieMeuble(String nomCategorieMeuble) {
        this.nomCategorieMeuble = nomCategorieMeuble;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Integer getIdMateriau() {
        return idMateriau;
    }

    public void setIdMateriau(Integer idMateriau) {
        this.idMateriau = idMateriau;
    }

    public String getNomTailleMeuble() {
        return nomTailleMeuble;
    }

    public void setNomTailleMeuble(String nomTailleMeuble) {
        this.nomTailleMeuble = nomTailleMeuble;
    }

    public static List<VMeubleContenantMateriau> selectByIdMateriau(Connection connection, Integer idMateriau)
            throws Exception {
        List<VMeubleContenantMateriau> meubles = new ArrayList<>();
        String query = "SELECT * FROM v_meuble_contenant_materiau WHERE id_materiau = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idMateriau);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            Integer idMeuble = resultSet.getInt("id_meuble");
            Integer idTailleMeuble = resultSet.getInt("id_taille_meuble");
            String nomMeuble = resultSet.getString("nom_meuble");
            Integer idStyleMeuble = resultSet.getInt("id_style_meuble");
            Integer idCategorieMeuble = resultSet.getInt("id_categorie_meuble");
            String nomStyleMeuble = resultSet.getString("nom_style_meuble");
            String nomCategorieMeuble = resultSet.getString("nom_categorie_meuble");
            Double quantite = resultSet.getDouble("quantite");
            String nomTailleMeuble = resultSet.getString("nom_taille_meuble");

            VMeubleContenantMateriau meuble = new VMeubleContenantMateriau(
                    id, idMeuble, idTailleMeuble, nomMeuble, idStyleMeuble, idCategorieMeuble,
                    nomStyleMeuble, nomCategorieMeuble, quantite, idMateriau, nomTailleMeuble);

            meubles.add(meuble);
        }
        statement.close();
        resultSet.close();
        return meubles;
    }
}
