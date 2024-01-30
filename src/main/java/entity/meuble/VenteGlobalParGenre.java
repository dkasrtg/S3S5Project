package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VenteGlobalParGenre {
    private Integer idGenre;
    private String nomGenre;
    private Double quantite;
    private Double pourcentage;

    public VenteGlobalParGenre(Integer idGenre, String nomGenre, Double quantite, Double pourcentage) {
        setIdGenre(idGenre);
        setNomGenre(nomGenre);
        setQuantite(quantite);
        setPourcentage(pourcentage);
    }

    public Integer getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(Integer idGenre) {
        this.idGenre = idGenre;
    }

    public String getNomGenre() {
        return nomGenre;
    }

    public void setNomGenre(String nomGenre) {
        this.nomGenre = nomGenre;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(Double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public static List<VenteGlobalParGenre> selectByDate(Connection connection, LocalDateTime dateDebut,
            LocalDateTime dateFin) throws Exception {
        List<VenteGlobalParGenre> venteGlobalParGenres  =new ArrayList<>();
        String query = "SELECT\r\n" + //
                "    g.id as id_genre,\r\n" + //
                "    g.nom as nom_genre,\r\n" + //
                "    COALESCE(q1.quantite, 0) as quantite,\r\n" + //
                "    COALESCE(q1.quantite / NULLIF(q2.total_quantite, 0) * 100, 0) as pourcentage\r\n" + //
                "FROM\r\n" + //
                "    genre g\r\n" + //
                "LEFT JOIN (\r\n" + //
                "    SELECT\r\n" + //
                "        sum(dvm.quantite) as quantite,\r\n" + //
                "        vc.id_genre\r\n" + //
                "    FROM\r\n" + //
                "        detail_vente_meuble dvm\r\n" + //
                "    JOIN vente_meuble vm ON vm.id = dvm.id_vente_meuble\r\n" + //
                "    JOIN v_client vc ON vc.id = vm.id_client\r\n" + //
                "    WHERE\r\n" + //
                "        vm.date_vente >= ? AND vm.date_vente <= ?\r\n" + //
                "    GROUP BY\r\n" + //
                "        vc.id_genre\r\n" + //
                ") q1 ON q1.id_genre = g.id\r\n" + //
                "LEFT JOIN (\r\n" + //
                "    SELECT\r\n" + //
                "        sum(dvm.quantite) as total_quantite,\r\n" + //
                "        vc.id_genre\r\n" + //
                "    FROM\r\n" + //
                "        detail_vente_meuble dvm\r\n" + //
                "    JOIN vente_meuble vm ON vm.id = dvm.id_vente_meuble\r\n" + //
                "    JOIN v_client vc ON vc.id = vm.id_client\r\n" + //
                "    WHERE\r\n" + //
                "        vm.date_vente >= ? AND vm.date_vente <= ?\r\n" + //
                "    GROUP BY\r\n" + //
                "        vc.id_genre\r\n" + //
                ") q2 ON q2.id_genre = g.id";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1, dateDebut);
        preparedStatement.setObject(2, dateFin);
        preparedStatement.setObject(3, dateDebut);
        preparedStatement.setObject(4, dateFin);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Integer idGenre = resultSet.getInt("id_genre");
            String nomGenre = resultSet.getString("nom_genre");
            Double quantite = resultSet.getDouble("quantite");
            Double pourcentage = resultSet.getDouble("pourcentage");
            VenteGlobalParGenre venteGlobalParGenre = new VenteGlobalParGenre(idGenre, nomGenre, quantite, pourcentage);
            venteGlobalParGenres.add(venteGlobalParGenre);
        }
        preparedStatement.close();
        resultSet.close();
        return venteGlobalParGenres;
    }
}
