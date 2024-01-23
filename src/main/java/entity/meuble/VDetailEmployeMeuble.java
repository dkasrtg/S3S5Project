package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VDetailEmployeMeuble {
    Integer id;
    Integer idFormuleMeuble;
    Integer idEmploye;
    Integer nombre;
    Double duree;
    String nomEmploye;

    public VDetailEmployeMeuble(Integer id, Integer idFormuleMeuble, Integer idEmploye, Integer nombre, Double duree,
            String nomEmploye) {
        setId(id);
        setIdFormuleMeuble(idFormuleMeuble);
        setIdEmploye(idEmploye);
        setNombre(nombre);
        setDuree(duree);
        setNomEmploye(nomEmploye);
    }

    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }

    public String getNomEmploye() {
        return nomEmploye;
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

    public static List<VDetailEmployeMeuble> selectByIdFormuleMeuble(Connection connection, Integer idFormuleMeuble)
            throws Exception {
        List<VDetailEmployeMeuble> details = new ArrayList<>();
        String query = "SELECT * FROM v_detail_employe_meuble WHERE id_formule_meuble = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idFormuleMeuble);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            Integer idEmploye = resultSet.getInt("id_employe");
            Integer nombre = resultSet.getInt("nombre");
            Double duree = resultSet.getDouble("duree");
            String nomEmploye = resultSet.getString("nom_employe");
            VDetailEmployeMeuble detail = new VDetailEmployeMeuble(id, idFormuleMeuble, idEmploye, nombre, duree,
                    nomEmploye);
            details.add(detail);
        }
        statement.close();
        resultSet.close();
        return details;
    }
}
