package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VDetailFormuleMeuble {
    private Integer id;
    private Integer idFormuleMeuble;
    private Integer idMateriau;
    private Double quantite;
    private String nomMateriau;

    public VDetailFormuleMeuble() {
    }

    public VDetailFormuleMeuble(Integer id,Integer idFormuleMeuble, Integer idMateriau, Double quantite,String nomMateriau) {
        setId(id);
        setIdFormuleMeuble(idFormuleMeuble);
        setIdMateriau(idMateriau);
        setQuantite(quantite);
        setNomMateriau(nomMateriau);
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

    public Integer getIdMateriau() {
        return idMateriau;
    }

    public void setIdMateriau(Integer idMateriau) {
        this.idMateriau = idMateriau;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public void setNomMateriau(String nomMateriau) {
        this.nomMateriau = nomMateriau;
    }

    public String getNomMateriau() {
        return nomMateriau;
    }

    public static List<VDetailFormuleMeuble> selectByIdFormuleMeuble(Connection connection, Integer idFormuleMeuble)
            throws SQLException {
        List<VDetailFormuleMeuble> vDetailFormuleMeubles = new ArrayList<>();
        String query = "SELECT * FROM v_detail_formule_meuble WHERE id_formule_meuble = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idFormuleMeuble);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Integer id = resultSet.getInt("id");
                    Integer idMateriau = resultSet.getInt("id_materiau");
                    Double quantite = resultSet.getDouble("quantite");
                    String nomMateriau = resultSet.getString("nom_materiau");
                    VDetailFormuleMeuble vDetailFormuleMeuble = new VDetailFormuleMeuble(id, idFormuleMeuble, idMateriau,
                            quantite, nomMateriau);
                    vDetailFormuleMeubles.add(vDetailFormuleMeuble);
                }
            }
        }

        return vDetailFormuleMeubles;
    }
}
