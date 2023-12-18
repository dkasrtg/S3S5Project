package entity.meuble;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VMeuble {
    private Integer id;
    private String nom;
    private Integer idStyleMeuble;
    private Integer idCategorieMeuble;
    private Double longueur;
    private Double largeur;
    private Double hauteur;
    private Double volume;
    private Double volumeMateriau;
    private String description;
    private String nomStyleMeuble;
    private String nomCategorieMeuble;
    private List<VLieuPossibleMeuble> vLieuPossibleMeuble;
    private List<VComposantMeuble> vComposantMeuble;

    public VMeuble() {
    }

    public VMeuble(Integer id, String nom, Integer idStyleMeuble, Integer idCategorieMeuble, Double longueur,
            Double largeur,
            Double hauteur, Double volume, Double volumeMateriau, String description, String nomStyleMeuble,
            String nomCategorieMeuble) {
        setId(id);
        setNom(nom);
        setIdStyleMeuble(idStyleMeuble);
        setIdCategorieMeuble(idCategorieMeuble);
        setLongueur(longueur);
        setLargeur(largeur);
        setHauteur(hauteur);
        setVolume(volume);
        setVolumeMateriau(volumeMateriau);
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

    public Double getLongueur() {
        return longueur;
    }

    public void setLongueur(Double longueur) {
        this.longueur = longueur;
    }

    public Double getLargeur() {
        return largeur;
    }

    public void setLargeur(Double largeur) {
        this.largeur = largeur;
    }

    public Double getHauteur() {
        return hauteur;
    }

    public void setHauteur(Double hauteur) {
        this.hauteur = hauteur;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getVolumeMateriau() {
        return volumeMateriau;
    }

    public void setVolumeMateriau(Double volumeMateriau) {
        this.volumeMateriau = volumeMateriau;
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

    public List<VLieuPossibleMeuble> getVLieuPossibleMeuble() {
        return vLieuPossibleMeuble;
    }

    public void setVLieuPossibleMeuble(List<VLieuPossibleMeuble> vLieuPossibleMeuble) {
        this.vLieuPossibleMeuble = vLieuPossibleMeuble;
    }

    public List<VComposantMeuble> getVComposantMeuble() {
        return vComposantMeuble;
    }

    public void setVComposantMeuble(List<VComposantMeuble> vComposantMeuble) {
        this.vComposantMeuble = vComposantMeuble;
    }

    public static List<VMeuble> list(Connection connection) throws SQLException {
        List<VMeuble> vMeubleList = new ArrayList<>();
        String query = "SELECT * FROM v_meuble";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            Integer idStyleMeuble = resultSet.getInt("id_style_meuble");
            Integer idCategorieMeuble = resultSet.getInt("id_categorie_meuble");
            Double longueur = resultSet.getDouble("longueur");
            Double largeur = resultSet.getDouble("largeur");
            Double hauteur = resultSet.getDouble("hauteur");
            Double volume = resultSet.getDouble("volume");
            Double volumeMateriau = resultSet.getDouble("volume_materiau");
            String description = resultSet.getString("description");
            String nomStyleMeuble = resultSet.getString("nom_style_meuble");
            String nomCategorieMeuble = resultSet.getString("nom_categorie_meuble");

            VMeuble vMeuble = new VMeuble(id, nom, idStyleMeuble, idCategorieMeuble, longueur, largeur, hauteur,
                    volume, volumeMateriau, description, nomStyleMeuble, nomCategorieMeuble);

            vMeubleList.add(vMeuble);
        }
        statement.close();
        resultSet.close();
        return vMeubleList;
    }

    public static VMeuble selectById(Connection connection, Integer id) throws SQLException {
        String query = "SELECT * FROM v_meuble WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String nom = resultSet.getString("nom");
            Integer idStyleMeuble = resultSet.getInt("id_style_meuble");
            Integer idCategorieMeuble = resultSet.getInt("id_categorie_meuble");
            Double longueur = resultSet.getDouble("longueur");
            Double largeur = resultSet.getDouble("largeur");
            Double hauteur = resultSet.getDouble("hauteur");
            Double volume = resultSet.getDouble("volume");
            Double volumeMateriau = resultSet.getDouble("volume_materiau");
            String description = resultSet.getString("description");
            String nomStyleMeuble = resultSet.getString("nom_style_meuble");
            String nomCategorieMeuble = resultSet.getString("nom_categorie_meuble");
            VMeuble vMeuble = new VMeuble(id, nom, idStyleMeuble, idCategorieMeuble, longueur, largeur, hauteur,
                    volume, volumeMateriau, description, nomStyleMeuble, nomCategorieMeuble);
            return vMeuble;
        }
        return null;
    }

}
