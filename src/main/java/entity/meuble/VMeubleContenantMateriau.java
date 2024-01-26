package entity.meuble;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.genericdao.GenericDAO;
import com.genericdao.annotation.Column;
import com.genericdao.annotation.Table;



@Table( name = "v_meuble_contenant_materiau" )
public class VMeubleContenantMateriau extends GenericDAO {

    @Column( name = "id")
	private Integer id;

	@Column( name = "id_meuble")
	private Integer idMeuble;

	@Column( name = "id_taille_meuble")
	private Integer idTailleMeuble;

	@Column( name = "nom_meuble")
	private String nomMeuble;

	@Column( name = "id_style_meuble")
	private Integer idStyleMeuble;

	@Column( name = "id_categorie_meuble")
	private Integer idCategorieMeuble;

	@Column( name = "nom_style_meuble")
	private String nomStyleMeuble;

	@Column( name = "nom_categorie_meuble")
	private String nomCategorieMeuble;

	@Column( name = "quantite")
	private Double quantite;

	@Column( name = "id_materiau")
	private Integer idMateriau;

	@Column( name = "nom_taille_meuble")
	private String nomTailleMeuble;

	

    public VMeubleContenantMateriau() {

    }

    public VMeubleContenantMateriau(Integer id, Integer idMeuble, Integer idTailleMeuble, String nomMeuble, Integer idStyleMeuble, Integer idCategorieMeuble, String nomStyleMeuble, String nomCategorieMeuble, Double quantite, Integer idMateriau, String nomTailleMeuble) {
        setId(id);
		setIdMeuble(idMeuble);
		setIdTailleMeuble(idTailleMeuble);
		setNomMeuble(nomMeuble);
		setIdStyleMeuble(idStyleMeuble);
		setIdCategorieMeuble(idCategorieMeuble);
		setNomStyleMeuble(nomStyleMeuble);
		setNomCategorieMeuble(nomCategorieMeuble);
		setQuantite(quantite);
		setIdMateriau(idMateriau);
		setNomTailleMeuble(nomTailleMeuble);
		
    }

    public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
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

	public void setIdStyleMeuble(Integer idStyleMeuble) {
		this.idStyleMeuble = idStyleMeuble;
	}

	public Integer getIdStyleMeuble() {
		return idStyleMeuble;
	}

	public void setIdCategorieMeuble(Integer idCategorieMeuble) {
		this.idCategorieMeuble = idCategorieMeuble;
	}

	public Integer getIdCategorieMeuble() {
		return idCategorieMeuble;
	}

	public void setNomStyleMeuble(String nomStyleMeuble) {
		this.nomStyleMeuble = nomStyleMeuble;
	}

	public String getNomStyleMeuble() {
		return nomStyleMeuble;
	}

	public void setNomCategorieMeuble(String nomCategorieMeuble) {
		this.nomCategorieMeuble = nomCategorieMeuble;
	}

	public String getNomCategorieMeuble() {
		return nomCategorieMeuble;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setIdMateriau(Integer idMateriau) {
		this.idMateriau = idMateriau;
	}

	public Integer getIdMateriau() {
		return idMateriau;
	}

	public void setNomTailleMeuble(String nomTailleMeuble) {
		this.nomTailleMeuble = nomTailleMeuble;
	}

	public String getNomTailleMeuble() {
		return nomTailleMeuble;
	}

	public static List<VMeubleContenantMateriau> selectByIdMateriau(Connection connection, Integer idMateriau)
            throws Exception {
        String query = "SELECT * FROM v_meuble_contenant_materiau WHERE id_materiau = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, idMateriau);
        return VMeubleContenantMateriau.selectMultipleByPreparedStatement(VMeubleContenantMateriau.class, statement, connection);
    }

}