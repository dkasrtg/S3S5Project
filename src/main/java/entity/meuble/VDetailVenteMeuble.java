package entity.meuble;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.genericdao.GenericDAO;
import com.genericdao.annotation.Column;
import com.genericdao.annotation.Table;



@Table( name = "v_detail_vente_meuble" )
public class VDetailVenteMeuble extends GenericDAO {

    @Column( name = "id")
	private Integer id;

	@Column( name = "id_vente_meuble")
	private Integer idVenteMeuble;

	@Column( name = "id_formule_meuble")
	private Integer idFormuleMeuble;

	@Column( name = "quantite")
	private Double quantite;

	@Column( name = "prix_unitaire")
	private Double prixUnitaire;

	@Column( name = "prix_total")
	private Double prixTotal;

	@Column( name = "id_meuble")
	private Integer idMeuble;

	@Column( name = "id_taille_meuble")
	private Integer idTailleMeuble;

	@Column( name = "nom_meuble")
	private String nomMeuble;

	@Column( name = "nom_taille_meuble")
	private String nomTailleMeuble;

	

    public VDetailVenteMeuble() {

    }

    public VDetailVenteMeuble(Integer id, Integer idVenteMeuble, Integer idFormuleMeuble, Double quantite, Double prixUnitaire, Double prixTotal, Integer idMeuble, Integer idTailleMeuble, String nomMeuble, String nomTailleMeuble) {
        setId(id);
		setIdVenteMeuble(idVenteMeuble);
		setIdFormuleMeuble(idFormuleMeuble);
		setQuantite(quantite);
		setPrixUnitaire(prixUnitaire);
		setPrixTotal(prixTotal);
		setIdMeuble(idMeuble);
		setIdTailleMeuble(idTailleMeuble);
		setNomMeuble(nomMeuble);
		setNomTailleMeuble(nomTailleMeuble);
		
    }

    public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setIdVenteMeuble(Integer idVenteMeuble) {
		this.idVenteMeuble = idVenteMeuble;
	}

	public Integer getIdVenteMeuble() {
		return idVenteMeuble;
	}

	public void setIdFormuleMeuble(Integer idFormuleMeuble) {
		this.idFormuleMeuble = idFormuleMeuble;
	}

	public Integer getIdFormuleMeuble() {
		return idFormuleMeuble;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public Double getPrixTotal() {
		return prixTotal;
	}

	public void setIdMeuble(Integer idMeuble) {
		this.idMeuble = idMeuble;
	}

	public Integer getIdMeuble() {
		return idMeuble;
	}

	public void setIdTailleMeuble(Integer idTailleMeuble) {
		this.idTailleMeuble = idTailleMeuble;
	}

	public Integer getIdTailleMeuble() {
		return idTailleMeuble;
	}

	public void setNomMeuble(String nomMeuble) {
		this.nomMeuble = nomMeuble;
	}

	public String getNomMeuble() {
		return nomMeuble;
	}

	public void setNomTailleMeuble(String nomTailleMeuble) {
		this.nomTailleMeuble = nomTailleMeuble;
	}

	public String getNomTailleMeuble() {
		return nomTailleMeuble;
	}

	public static List<VDetailVenteMeuble> seelctByIdVenteMeuble(Connection connection, Integer idVenteMeuble) throws Exception {
		String query = "select * from v_detail_vente_meuble where id_vente_meuble = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, idVenteMeuble);
		return VDetailEmployeMeuble.selectMultipleByPreparedStatement(VDetailVenteMeuble.class, preparedStatement, connection);
	}

}