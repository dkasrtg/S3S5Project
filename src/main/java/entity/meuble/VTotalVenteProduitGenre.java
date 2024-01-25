package entity.meuble;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.genericdao.*;
import com.genericdao.annotation.*;



@Table( name = "v_total_vente_produit_genre" )
public class VTotalVenteProduitGenre extends GenericDAO {

    @Column( name = "id_formule_meuble")
	private Integer idFormuleMeuble;

	@Column( name = "id_taille_meuble")
	private Integer idTailleMeuble;

	@Column( name = "id_meuble")
	private Integer idMeuble;

	@Column( name = "id_genre")
	private Integer idGenre;

	@Column( name = "genre")
	private String genre;

	@Column( name = "quantite")
	private Double quantite;

	@Column( name = "nom_taille_meuble")
	private String nomTailleMeuble;

	@Column( name = "nom_meuble")
	private String nomMeuble;

	

    public VTotalVenteProduitGenre() {

    }

    public VTotalVenteProduitGenre(Integer idFormuleMeuble, Integer idTailleMeuble, Integer idMeuble, Integer idGenre, String genre, Double quantite, String nomTailleMeuble, String nomMeuble) {
        setIdFormuleMeuble(idFormuleMeuble);
		setIdTailleMeuble(idTailleMeuble);
		setIdMeuble(idMeuble);
		setIdGenre(idGenre);
		setGenre(genre);
		setQuantite(quantite);
		setNomTailleMeuble(nomTailleMeuble);
		setNomMeuble(nomMeuble);
		
    }

    public void setIdFormuleMeuble(Integer idFormuleMeuble) {
		this.idFormuleMeuble = idFormuleMeuble;
	}

	public Integer getIdFormuleMeuble() {
		return idFormuleMeuble;
	}

	public void setIdTailleMeuble(Integer idTailleMeuble) {
		this.idTailleMeuble = idTailleMeuble;
	}

	public Integer getIdTailleMeuble() {
		return idTailleMeuble;
	}

	public void setIdMeuble(Integer idMeuble) {
		this.idMeuble = idMeuble;
	}

	public Integer getIdMeuble() {
		return idMeuble;
	}

	public void setIdGenre(Integer idGenre) {
		this.idGenre = idGenre;
	}

	public Integer getIdGenre() {
		return idGenre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getGenre() {
		return genre;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setNomTailleMeuble(String nomTailleMeuble) {
		this.nomTailleMeuble = nomTailleMeuble;
	}

	public String getNomTailleMeuble() {
		return nomTailleMeuble;
	}

	public void setNomMeuble(String nomMeuble) {
		this.nomMeuble = nomMeuble;
	}

	public String getNomMeuble() {
		return nomMeuble;
	}

	public static List<VTotalVenteProduitGenre> selectByIdFormuleMeuble(Connection connection, Integer idFormuleMeuble) throws SQLException {
        List<VTotalVenteProduitGenre> resultList = new ArrayList<>();
        String query = "SELECT * FROM v_total_vente_produit_genre WHERE id_formule_meuble = ? order by id_genre asc";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idFormuleMeuble);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    VTotalVenteProduitGenre result = mapResultSetToVTotalVenteProduitGenre(resultSet);
                    resultList.add(result);
                }
            }
        }

        return resultList;
    }

    private static VTotalVenteProduitGenre mapResultSetToVTotalVenteProduitGenre(ResultSet resultSet) throws SQLException {
        Integer idFormuleMeuble = resultSet.getInt("id_formule_meuble");
        Integer idTailleMeuble = resultSet.getInt("id_taille_meuble");
        Integer idMeuble = resultSet.getInt("id_meuble");
        Integer idGenre = resultSet.getInt("id_genre");
        String genre = resultSet.getString("genre");
        Double quantite = resultSet.getDouble("quantite");
        String nomTailleMeuble = resultSet.getString("nom_taille_meuble");
        String nomMeuble = resultSet.getString("nom_meuble");

        return new VTotalVenteProduitGenre(idFormuleMeuble, idTailleMeuble, idMeuble, idGenre, genre, quantite, nomTailleMeuble, nomMeuble);
    }

}