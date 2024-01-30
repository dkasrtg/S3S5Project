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
                "total_quantite,\r\n" + //
                "id_genre,\r\n" + //
                "nom_genre,\r\n" + //
                "CASE\r\n" + //
                "    WHEN SUM(total_quantite) OVER () = 0 THEN 0\r\n" + //
                "    ELSE total_quantite * 100.0 / SUM(total_quantite) OVER ()\r\n" + //
                "END as pourcentage\r\n" + //
                "FROM\r\n" + //
                "(SELECT\r\n" + //
                "    sum(dvm.quantite) as total_quantite,\r\n" + //
                "    vc.id_genre,vc.genre as nom_genre\r\n" + //
                "FROM\r\n" + //
                "    detail_vente_meuble dvm\r\n" + //
                "JOIN vente_meuble vm ON vm.id = dvm.id_vente_meuble\r\n" + //
                "JOIN v_client vc ON vc.id = vm.id_client\r\n" + //
                "WHERE\r\n" + //
                "    vm.date_vente >= ? AND vm.date_vente <= ? \r\n" + //
                "GROUP BY\r\n" + //
                "    vc.id_genre,vc.genre) as q1;\r\n" + //
                "";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setObject(1, dateDebut);
        preparedStatement.setObject(2, dateFin);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Integer idGenre = resultSet.getInt("id_genre");
            String nomGenre = resultSet.getString("nom_genre");
            Double quantite = resultSet.getDouble("total_quantite");
            Double pourcentage = resultSet.getDouble("pourcentage");
            VenteGlobalParGenre venteGlobalParGenre = new VenteGlobalParGenre(idGenre, nomGenre, quantite, pourcentage);
            venteGlobalParGenres.add(venteGlobalParGenre);
        }
        preparedStatement.close();
        resultSet.close();
        return venteGlobalParGenres;
    }
}
