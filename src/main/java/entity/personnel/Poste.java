package entity.personnel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Poste {
    private Integer id;
    private String nom;

    

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



    public static List<Poste> listPostes(Connection connection) throws Exception {
        List<Poste> postes = new ArrayList<>();
        String query = "SELECT * FROM poste";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");

                Poste poste = new Poste();
                poste.setId(id);
                poste.setNom(nom);

                postes.add(poste);
            }
        }
        return postes;
    }
}
