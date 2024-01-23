package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;

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

    public void insert(Connection connection) throws Exception {
        String query = "INSERT INTO detail_employe_meuble (id_formule_meuble, id_employe, nombre, duree) " +
                "VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, getIdFormuleMeuble());
        statement.setInt(2, getIdEmploye());
        statement.setInt(3, getNombre());
        statement.setDouble(4, getDuree());
        statement.executeUpdate();
        statement.close();
    }
}
