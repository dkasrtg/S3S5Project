package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.genericdao.GenericDAO;
import com.genericdao.annotation.Column;
import com.genericdao.annotation.Table;

@Table(name = "v_detail_employe_meuble")
public class VDetailEmployeMeuble extends GenericDAO {

	@Column(name = "id")
	private Integer id;

	@Column(name = "id_formule_meuble")
	private Integer idFormuleMeuble;

	@Column(name = "id_poste")
	private Integer idPoste;

	@Column(name = "id_niveau")
	private Integer idNiveau;

	@Column(name = "nombre")
	private Integer nombre;

	@Column(name = "duree")
	private Double duree;

	@Column(name = "nom_poste")
	private String nomPoste;

	@Column(name = "nom_niveau")
	private String nomNiveau;

	@Column(name = "ordre_niveau")
	private Integer ordreNiveau;

	public VDetailEmployeMeuble() {

	}

	public VDetailEmployeMeuble(Integer id, Integer idFormuleMeuble, Integer idPoste, Integer idNiveau, Integer nombre,
			Double duree, String nomPoste, String nomNiveau, Integer ordreNiveau) {
		setId(id);
		setIdFormuleMeuble(idFormuleMeuble);
		setIdPoste(idPoste);
		setIdNiveau(idNiveau);
		setNombre(nombre);
		setDuree(duree);
		setNomPoste(nomPoste);
		setNomNiveau(nomNiveau);
		setOrdreNiveau(ordreNiveau);

	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setIdFormuleMeuble(Integer idFormuleMeuble) {
		this.idFormuleMeuble = idFormuleMeuble;
	}

	public Integer getIdFormuleMeuble() {
		return idFormuleMeuble;
	}

	public void setIdPoste(Integer idPoste) {
		this.idPoste = idPoste;
	}

	public Integer getIdPoste() {
		return idPoste;
	}

	public void setIdNiveau(Integer idNiveau) {
		this.idNiveau = idNiveau;
	}

	public Integer getIdNiveau() {
		return idNiveau;
	}

	public void setNombre(Integer nombre) {
		this.nombre = nombre;
	}

	public Integer getNombre() {
		return nombre;
	}

	public void setDuree(Double duree) {
		this.duree = duree;
	}

	public Double getDuree() {
		return duree;
	}

	public void setNomPoste(String nomPoste) {
		this.nomPoste = nomPoste;
	}

	public String getNomPoste() {
		return nomPoste;
	}

	public void setNomNiveau(String nomNiveau) {
		this.nomNiveau = nomNiveau;
	}

	public String getNomNiveau() {
		return nomNiveau;
	}

	public void setOrdreNiveau(Integer ordreNiveau) {
		this.ordreNiveau = ordreNiveau;
	}

	public Integer getOrdreNiveau() {
		return ordreNiveau;
	}

	public static List<VDetailEmployeMeuble> selectByIdFormuleMeuble(Connection connection, Integer idFormuleMeuble)
			throws Exception {
		String query = "SELECT * FROM v_detail_employe_meuble WHERE id_formule_meuble = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, idFormuleMeuble);
		return VDetailEmployeMeuble.selectMultipleByPreparedStatement(VDetailEmployeMeuble.class, statement,
				connection);
	}

}