package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetailEmployeMeuble {
    Integer id;
    Integer idFormuleMeuble;
    Integer idEmploye;
    Integer nombre;
    Double duree;

    public DetailEmployeMeuble(Integer id, Integer idFormuleMeuble, Integer idEmploye, Integer nombre, Double duree) {
        setId(id);
        setIdFormuleMeuble(idFormuleMeuble);
        setIdEmploye(idEmploye);
        setNombre(nombre);
        setDuree(duree);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdFormuleMeuble() {
        return idFormuleMeuble;
    }

    public void setIdFormuleMeuble(Integer idFormuleMeuble) {
        this.idFormuleMeuble = idFormuleMeuble;
    }

    public Integer getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(Integer idEmploye) {
        this.idEmploye = idEmploye;
    }

    public Integer getNombre() {
        return nombre;
    }

    public void setNombre(Integer nombre) {
        this.nombre = nombre;
    }

    public Double getDuree() {
        return duree;
    }

    public void setDuree(Double duree) {
        this.duree = duree;
    }

    public void insert(Connection connection) throws SQLException {
        String query = "INSERT INTO detail_employe_meuble (id_formule_meuble, id_employe, nombre, duree) " +
                "VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, getIdFormuleMeuble());
            statement.setInt(2, getIdEmploye());
            statement.setInt(3, getNombre());
            statement.setDouble(4, getDuree());

            statement.executeUpdate();
        }
    }

    public static List<DetailEmployeMeuble> selectByIdFormuleMeuble(Connection connection, Integer idFormuleMeuble)
            throws SQLException {
        List<DetailEmployeMeuble> details = new ArrayList<>();
        String query = "SELECT * FROM detail_employe_meuble WHERE id_formule_meuble = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idFormuleMeuble);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    Integer idEmploye = resultSet.getInt("id_employe");
                    Integer nombre = resultSet.getInt("nombre");
                    Double duree = resultSet.getDouble("duree");
                    DetailEmployeMeuble detail = new DetailEmployeMeuble(id,idFormuleMeuble,idEmploye,nombre,duree);
                    details.add(detail);
                }
            }
        }
        return details;
    }
}
