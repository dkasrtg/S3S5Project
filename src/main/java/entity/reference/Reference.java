package entity.reference;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Reference {
    Integer id;
    String string;
    LocalDate dateSimple;
    LocalTime heureSimple;
    LocalDateTime dateHeure;
    Integer entier;
    Double pasEntier;
    Integer idOptionReference;

    public Reference() {
    }

    public Reference(Integer id, String string, LocalDate dateSimple, LocalTime heureSimple, LocalDateTime dateHeure,
            Integer entier, Double pasEntier, Integer idOptionReference) {
        setId(id);
        setString(string);
        setDateSimple(dateSimple);
        setHeureSimple(heureSimple);
        setDateHeure(dateHeure);
        setEntier(entier);
        setPasEntier(pasEntier);
        setIdOptionReference(idOptionReference);
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

    public void insert(Connection connection) {
        try {
            String sql = "INSERT INTO reference (string, date_simple, heure_simple, date_heure, entier, pas_entier, id_option_reference) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, getString());
            statement.setObject(2, getDateSimple());
            statement.setObject(3, getHeureSimple());
            statement.setObject(4, getDateHeure());
            statement.setInt(5, getEntier());
            statement.setDouble(6, getPasEntier());
            statement.setInt(7, getIdOptionReference());
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    setId(generatedKeys.getInt(1));
                }
                generatedKeys.close();
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(Connection connection, int id) {
        try {
            String sql = "DELETE FROM reference WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Connection connection) {
        try {
            String sql = "UPDATE reference SET string = ?, date_simple = ?, heure_simple = ?, date_heure = ?, entier = ?, pas_entier = ?, id_option_reference = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, getString());
            statement.setObject(2, getDateSimple());
            statement.setObject(3, getHeureSimple());
            statement.setObject(4, getDateHeure());
            statement.setInt(5, getEntier());
            statement.setDouble(6, getPasEntier());
            statement.setInt(7, getIdOptionReference());
            statement.setInt(8, getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}