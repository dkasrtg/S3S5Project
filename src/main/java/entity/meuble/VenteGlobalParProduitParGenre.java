package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VenteGlobalParProduitParGenre {
    Integer idFormuleMeuble;
    Integer idGenre;
    String nomGenre;
    Double quantite;
    Double pourcentage;

    
    public VenteGlobalParProduitParGenre(Integer idFormuleMeuble, Integer idGenre, String nomGenre, Double quantite,
            Double pourcentage) {
        setIdFormuleMeuble(idFormuleMeuble);
        setIdGenre(idGenre);
        setNomGenre(nomGenre);
        setQuantite(quantite);
        setPourcentage(pourcentage);
    }
    public Integer getIdFormuleMeuble() {
        return idFormuleMeuble;
    }
    public void setIdFormuleMeuble(Integer idFormuleMeuble) {
        this.idFormuleMeuble = idFormuleMeuble;
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
    
    public static List<VenteGlobalParProduitParGenre> seelctByDateAndIdFormuleMeuble(Connection connection,LocalDateTime dateDebut,LocalDateTime dateFin,Integer idFormuleMeuble) throws Exception {
        String query = "SELECT\r\n" + //
                "vmg.*,\r\n" + //
                "coalesce(q1.quantite, 0) as quantite,\r\n" + //
                "CASE\r\n" + //
                "    WHEN SUM(coalesce(q1.quantite, 0)) OVER (PARTITION BY vmg.id_formule_meuble) = 0 THEN 0\r\n" + //
                "    ELSE coalesce(q1.quantite, 0) * 100.0 / SUM(coalesce(q1.quantite, 0)) OVER (PARTITION BY vmg.id_formule_meuble)\r\n" + //
                "END as pourcentage\r\n" + //
                "FROM\r\n" + //
                "(SELECT * FROM v_map_genre WHERE id_formule_meuble = ?) as vmg\r\n" + //
                "LEFT JOIN\r\n" + //
                "(SELECT\r\n" + //
                "    dvm.id_formule_meuble,\r\n" + //
                "    sum(dvm.quantite) as quantite,\r\n" + //
                "    vc.id_genre\r\n" + //
                "FROM\r\n" + //
                "    detail_vente_meuble dvm\r\n" + //
                "JOIN vente_meuble vm ON vm.id = dvm.id_vente_meuble\r\n" + //
                "JOIN v_client vc ON vc.id = vm.id_client\r\n" + //
                "WHERE\r\n" + //
                "    vm.date_vente >= ? AND vm.date_vente <= ?\r\n" + //
                "GROUP BY\r\n" + //
                "    dvm.id_formule_meuble, vc.id_genre) as q1\r\n" + //
                "ON\r\n" + //
                "q1.id_formule_meuble = vmg.id_formule_meuble AND q1.id_genre = vmg.id_genre";
        List<VenteGlobalParProduitParGenre> venteGlobalParProduitParGenres = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idFormuleMeuble);
        preparedStatement.setObject(2, dateDebut);
        preparedStatement.setObject(3, dateFin);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Integer idGenre = resultSet.getInt("id_genre");
            String nomGenre = resultSet.getString("nom_genre");
            Double quantite = resultSet.getDouble("quantite");
            Double pourcentage = resultSet.getDouble("pourcentage");
            VenteGlobalParProduitParGenre venteGlobalParProduitParGenre = new VenteGlobalParProduitParGenre(idFormuleMeuble, idGenre, nomGenre, quantite, pourcentage);
            venteGlobalParProduitParGenres.add(venteGlobalParProduitParGenre);
        }
        preparedStatement.close();
        resultSet.close();
        return venteGlobalParProduitParGenres;
    }    
}
