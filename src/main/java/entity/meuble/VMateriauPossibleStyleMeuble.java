package entity.meuble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.genericdao.GenericDAO;
import com.genericdao.annotation.Column;
import com.genericdao.annotation.Table;

@Table(name = "v_materiau_possible_style_meuble")
public class VMateriauPossibleStyleMeuble extends GenericDAO {

	@Column(name = "id")
	private Integer id;

	@Column(name = "id_style_meuble")
	private Integer idStyleMeuble;

	@Column(name = "id_materiau")
	private Integer idMateriau;

	@Column(name = "nom_materiau")
	private String nomMateriau;

	@Column(name = "id_type_materiau")
	private Integer idTypeMateriau;

	@Column(name = "nom_type_materiau")
	private String nomTypeMateriau;

	public VMateriauPossibleStyleMeuble() {

	}

	public VMateriauPossibleStyleMeuble(Integer id, Integer idStyleMeuble, Integer idMateriau, String nomMateriau,
			Integer idTypeMateriau, String nomTypeMateriau) {
		setId(id);
		setIdStyleMeuble(idStyleMeuble);
		setIdMateriau(idMateriau);
		setNomMateriau(nomMateriau);
		setIdTypeMateriau(idTypeMateriau);
		setNomTypeMateriau(nomTypeMateriau);

	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setIdStyleMeuble(Integer idStyleMeuble) {
		this.idStyleMeuble = idStyleMeuble;
	}

	public Integer getIdStyleMeuble() {
		return idStyleMeuble;
	}

	public void setIdMateriau(Integer idMateriau) {
		this.idMateriau = idMateriau;
	}

	public Integer getIdMateriau() {
		return idMateriau;
	}

	public void setNomMateriau(String nomMateriau) {
		this.nomMateriau = nomMateriau;
	}

	public String getNomMateriau() {
		return nomMateriau;
	}

	public void setIdTypeMateriau(Integer idTypeMateriau) {
		this.idTypeMateriau = idTypeMateriau;
	}

	public Integer getIdTypeMateriau() {
		return idTypeMateriau;
	}

	public void setNomTypeMateriau(String nomTypeMateriau) {
		this.nomTypeMateriau = nomTypeMateriau;
	}

	public String getNomTypeMateriau() {
		return nomTypeMateriau;
	}

	public static List<VMateriauPossibleStyleMeuble> selectByIdStyleMeuble(Connection connection, int idStyleMeuble)
			throws Exception {
		String query = "SELECT * FROM v_materiau_possible_style_meuble WHERE id_style_meuble = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, idStyleMeuble);
		return VMateriauPossibleStyleMeuble.selectMultipleByPreparedStatement(VMateriauPossibleStyleMeuble.class, statement, connection);
	}

}