package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MeubleContenantMateriau {
    private Integer id;
    private Integer idMeuble;
    private Integer idTailleMeuble;
    private String nomMeuble;
    private Integer idStyleMeuble;
    private Integer idCategorieMeuble;
    private String nomCategorieMeuble;
    private String nomStyleMeuble;
    private String nomTailleMeuble;
    private Double quantite;

    public MeubleContenantMateriau() {
    }

    public MeubleContenantMateriau(Integer id, Integer idMeuble, Integer idTailleMeuble, String nomMeuble,
            Integer idStyleMeuble, Integer idCategorieMeuble, String nomCategorieMeuble,
            String nomStyleMeuble, String nomTailleMeuble, Double quantite) {
        setId(id);
        setIdMeuble(idMeuble);
        setIdTailleMeuble(idTailleMeuble);
        setNomMeuble(nomMeuble);
        setIdStyleMeuble(idStyleMeuble);
        setIdCategorieMeuble(idCategorieMeuble);
        setNomCategorieMeuble(nomCategorieMeuble);
        setNomStyleMeuble(nomStyleMeuble);
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

    public String getNomCategorieMeuble() {
        return nomCategorieMeuble;
    }

    public void setNomCategorieMeuble(String nomCategorieMeuble) {
        this.nomCategorieMeuble = nomCategorieMeuble;
    }

    public String getNomStyleMeuble() {
        return nomStyleMeuble;
    }

    public void setNomStyleMeuble(String nomStyleMeuble) {
        this.nomStyleMeuble = nomStyleMeuble;
    }

    public String getNomTailleMeuble() {
        return nomTailleMeuble;
    }

    public void setNomTailleMeuble(String nomTailleMeuble) {
        this.nomTailleMeuble = nomTailleMeuble;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getQuantite() {
        return quantite;
    }

    public static List<MeubleContenantMateriau> selectByIdMateriau(Connection connection, Integer idMateriau)
            throws SQLException {
        List<MeubleContenantMateriau> formuleMeubles = new ArrayList<>();
        String query = "select fm.*,vm.nom as nom_meuble,vm.id_style_meuble,vm.id_categorie_meuble,vm.description,vm.nom_style_meuble,vm.nom_categorie_meuble,\r\n" + //
                "dfm.quantite,tm.nom as nom_taille_meuble\r\n" + //
                "from detail_formule_meuble dfm \r\n" + //
                "join formule_meuble fm on fm.id=dfm.id_formule_meuble \r\n" + //
                "join v_meuble vm on vm.id=fm.id_meuble\r\n" + //
                "join taille_meuble tm on tm.id=fm.id_taille_meuble\r\n" + //
                "where dfm.id_materiau = ?;\r\n" + //
                "\r\n" + //
                "";

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
            String nomCategorieMeuble = resultSet.getString("nom_categorie_meuble");
            String nomStyleMeuble = resultSet.getString("nom_style_meuble");
            String nomTailleMeuble = resultSet.getString("nom_taille_meuble");
            Double quantite = resultSet.getDouble("quantite");

            MeubleContenantMateriau formuleMeuble = new MeubleContenantMateriau(id, idMeuble, idTailleMeuble, nomMeuble, idStyleMeuble,
                    idCategorieMeuble, nomCategorieMeuble, nomStyleMeuble, nomTailleMeuble,quantite);
            formuleMeubles.add(formuleMeuble);
        }

        statement.close();
        resultSet.close();
        return formuleMeubles;
    }
}
