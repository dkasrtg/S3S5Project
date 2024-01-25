package entity.personnel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Niveau {
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



    public static List<Niveau> listNiveaux(Connection connection) throws Exception {
        List<Niveau> niveaux = new ArrayList<>();
        String query = "SELECT * FROM niveau";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");

                Niveau niveau = new Niveau();
                niveau.setId(id);
                niveau.setNom(nom);

                niveaux.add(niveau);
            }
        }
        return niveaux;
    }
}
