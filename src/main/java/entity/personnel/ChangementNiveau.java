package entity.personnel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChangementNiveau {
    private Integer id;
    private Integer idNiveauDepart;
    private Integer idNiveauArrive;
    private Double duree;

    public ChangementNiveau() {
    }

    public ChangementNiveau(Integer id, Integer idNiveauDepart, Integer idNiveauArrive, Double duree) {
        this.id = id;
        this.idNiveauDepart = idNiveauDepart;
        this.idNiveauArrive = idNiveauArrive;
        this.duree = duree;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdNiveauDepart() {
        return idNiveauDepart;
    }

    public void setIdNiveauDepart(Integer idNiveauDepart) {
        this.idNiveauDepart = idNiveauDepart;
    }

    public Integer getIdNiveauArrive() {
        return idNiveauArrive;
    }

    public void setIdNiveauArrive(Integer idNiveauArrive) {
        this.idNiveauArrive = idNiveauArrive;
    }

    public Double getDuree() {
        return duree;
    }

    public void setDuree(Double duree) {
        this.duree = duree;
    }

    public static List<ChangementNiveau> selectByIdGreaterThanOrEqual(Connection connection, Integer x) throws SQLException {
        String query = "SELECT * FROM changement_niveau WHERE id_niveau_depart >= ? order by id_niveau_depart asc";
        List<ChangementNiveau> changementNiveaus = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, x);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ChangementNiveau changementNiveau = new ChangementNiveau();
                    changementNiveau.setId(resultSet.getInt("id"));
                    changementNiveau.setIdNiveauDepart(resultSet.getInt("id_niveau_depart"));
                    changementNiveau.setIdNiveauArrive(resultSet.getInt("id_niveau_arrive"));
                    changementNiveau.setDuree(resultSet.getDouble("duree"));
                    changementNiveaus.add(changementNiveau);
                }
            }
        }
        return changementNiveaus;

    }

}
