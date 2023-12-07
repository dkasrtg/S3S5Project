package entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class VReference {
    Integer id;
    String string;
    LocalDate dateSimple;
    LocalTime heureSimple;
    LocalDateTime dateHeure;
    Integer entier;
    Double pasEntier;
    Integer idOptionReference;
    String option;

    public VReference() {
    }

    public VReference(Integer id, String string, LocalDate dateSimple, LocalTime heureSimple, LocalDateTime dateHeure,
            Integer entier, Double pasEntier, Integer idOptionReference, String option) {
        setId(id);
        setString(string);
        setDateSimple(dateSimple);
        setHeureSimple(heureSimple);
        setDateHeure(dateHeure);
        setEntier(entier);
        setPasEntier(pasEntier);
        setIdOptionReference(idOptionReference);
        setOption(option);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public LocalDate getDateSimple() {
        return dateSimple;
    }

    public void setDateSimple(LocalDate dateSimple) {
        this.dateSimple = dateSimple;
    }

    public LocalTime getHeureSimple() {
        return heureSimple;
    }

    public void setHeureSimple(LocalTime heureSimple) {
        this.heureSimple = heureSimple;
    }

    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }

    public Integer getEntier() {
        return entier;
    }

    public void setEntier(Integer entier) {
        this.entier = entier;
    }

    public Double getPasEntier() {
        return pasEntier;
    }

    public void setPasEntier(Double pasEntier) {
        this.pasEntier = pasEntier;
    }

    public Integer getIdOptionReference() {
        return idOptionReference;
    }

    public void setIdOptionReference(Integer idOptionReference) {
        this.idOptionReference = idOptionReference;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public static List<VReference> select(Connection connection) {
        List<VReference> vReferences = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, string, date_simple, heure_simple, date_heure, entier, pas_entier, id_option_reference, option FROM v_reference");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String string = resultSet.getString("string");
                LocalDate dateSimple = resultSet.getDate("date_simple").toLocalDate();
                LocalTime heureSimple = resultSet.getTime("heure_simple").toLocalTime();
                LocalDateTime dateHeure = resultSet.getTimestamp("date_heure").toLocalDateTime();
                int entier = resultSet.getInt("entier");
                double pasEntier = resultSet.getDouble("pas_entier");
                int idOptionReference = resultSet.getInt("id_option_reference");
                String option = resultSet.getString("option");

                VReference vReference = new VReference(id, string, dateSimple, heureSimple, dateHeure, entier,
                        pasEntier, idOptionReference, option);
                vReferences.add(vReference);
            }

            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vReferences;
    }

    public static VReference selectById(Connection connection, int id) {
        VReference vReference = null;
        try {
            String sql = "SELECT id, string, date_simple, heure_simple, date_heure, entier, pas_entier, id_option_reference, option FROM v_reference WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String string = resultSet.getString("string");
                LocalDate dateSimple = resultSet.getDate("date_simple").toLocalDate();
                LocalTime heureSimple = resultSet.getTime("heure_simple").toLocalTime();
                LocalDateTime dateHeure = resultSet.getTimestamp("date_heure").toLocalDateTime();
                int entier = resultSet.getInt("entier");
                double pasEntier = resultSet.getDouble("pas_entier");
                int idOptionReference = resultSet.getInt("id_option_reference");
                String option = resultSet.getString("option");

                vReference = new VReference(id, string, dateSimple, heureSimple, dateHeure, entier,
                        pasEntier, idOptionReference, option);
            }

            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vReference;
    }


}