package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VMeuble {
    private Integer id;
    private String nom;
    private Integer idStyleMeuble;
    private Integer idCategorieMeuble;
    private String description;
    private String nomStyleMeuble;
    private String nomCategorieMeuble;

    public VMeuble() {
    }

    public VMeuble(Integer id, String nom, Integer idStyleMeuble, Integer idCategorieMeuble, String description,
            String nomStyleMeuble, String nomCategorieMeuble) {
        setId(id);
        setNom(nom);
        setIdStyleMeuble(idStyleMeuble);
        setIdCategorieMeuble(idCategorieMeuble);
        setDescription(description);
        setNomStyleMeuble(nomStyleMeuble);
        setNomCategorieMeuble(nomCategorieMeuble);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public static List<VMeuble> list(Connection connection) throws Exception {
        List<VMeuble> vMeubleList = new ArrayList<>();
        String query = "SELECT * FROM v_meuble";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            Integer idStyleMeuble = resultSet.getInt("id_style_meuble");
            String description = resultSet.getString("description");
            String nomStyleMeuble = resultSet.getString("nom_style_meuble");
            String nomCategorieMeuble = resultSet.getString("nom_categorie_meuble");
            Integer idCategorieMeuble = resultSet.getInt("id_categorie_meuble");

            VMeuble vMeuble = new VMeuble(id, nom, idStyleMeuble, idCategorieMeuble, description, nomStyleMeuble,
                    nomCategorieMeuble);
            vMeubleList.add(vMeuble);
        }
        statement.close();
        resultSet.close();
        return vMeubleList;
    }

    public static VMeuble selectById(Connection connection, Integer id) throws Exception {
        String query = "SELECT * FROM v_meuble WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String nom = resultSet.getString("nom");
            Integer idStyleMeuble = resultSet.getInt("id_style_meuble");
            Integer idCategorieMeuble = resultSet.getInt("id_categorie_meuble");
            String description = resultSet.getString("description");
            String nomStyleMeuble = resultSet.getString("nom_style_meuble");
            String nomCategorieMeuble = resultSet.getString("nom_categorie_meuble");
            VMeuble vMeuble = new VMeuble(id, nom, idStyleMeuble, idCategorieMeuble,
                    description, nomStyleMeuble, nomCategorieMeuble);
            return vMeuble;
        }
        return null;
    }
}
