package entity.reference;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Checkbox {
    Integer id;
    String nom;

    public Checkbox() {
    }

    public Checkbox(Integer id, String nom) {
        setId(id);
        setNom(nom);
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

    public static List<Checkbox> select(Connection connection) {
        List<Checkbox> checkboxes = new ArrayList<>();

        try {
            String sql = "SELECT id, nom FROM checkbox";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");

                Checkbox checkbox = new Checkbox(id, nom);
                checkboxes.add(checkbox);
            }

            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return checkboxes;
    }

    public static List<Checkbox> select(Connection connection, Integer[] excludedIds) {
        List<Checkbox> checkboxes = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("SELECT id, nom FROM checkbox WHERE id NOT IN (");

            for (int i = 0; i < excludedIds.length; i++) {
                if (i > 0) {
                    sql.append(", ?");
                } else {
                    sql.append("?");
                }
            }
            sql.append(")");

            PreparedStatement statement = connection.prepareStatement(sql.toString());

            for (int i = 0; i < excludedIds.length; i++) {
                statement.setInt(i + 1, excludedIds[i]);
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");

                Checkbox checkbox = new Checkbox(id, nom);
                checkboxes.add(checkbox);
            }

            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return checkboxes;
    }

}
