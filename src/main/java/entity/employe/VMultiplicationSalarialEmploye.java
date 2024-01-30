package entity.employe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.genericdao.GenericDAO;
import com.genericdao.annotation.Column;
import com.genericdao.annotation.Table;

@Table(name = "v_multiplication_salarial_employe")
public class VMultiplicationSalarialEmploye extends GenericDAO {

	@Column(name = "id")
	private Integer id;

	@Column(name = "id_poste")
	private Integer idPoste;

	@Column(name = "id_niveau_depart")
	private Integer idNiveauDepart;

	@Column(name = "id_niveau_arrive")
	private Integer idNiveauArrive;

	@Column(name = "multipliant")
	private Double multipliant;

	@Column(name = "date_debut")
	private LocalDateTime dateDebut;

	@Column(name = "date_fin")
	private LocalDateTime dateFin;

	@Column(name = "nom_poste")
	private String nomPoste;

	@Column(name = "nom_niveau_depart")
	private String nomNiveauDepart;

	@Column(name = "nom_niveau_arrive")
	private String nomNiveauArrive;

	public VMultiplicationSalarialEmploye() {

	}

	public VMultiplicationSalarialEmploye(Integer id, Integer idPoste, Integer idNiveauDepart, Integer idNiveauArrive,
			Double multipliant, LocalDateTime dateDebut, LocalDateTime dateFin, String nomPoste, String nomNiveauDepart,
			String nomNiveauArrive) {
		setId(id);
		setIdPoste(idPoste);
		setIdNiveauDepart(idNiveauDepart);
		setIdNiveauArrive(idNiveauArrive);
		setMultipliant(multipliant);
		setDateDebut(dateDebut);
		setDateFin(dateFin);
		setNomPoste(nomPoste);
		setNomNiveauDepart(nomNiveauDepart);
		setNomNiveauArrive(nomNiveauArrive);

	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setIdPoste(Integer idPoste) {
		this.idPoste = idPoste;
	}

	public Integer getIdPoste() {
		return idPoste;
	}

	public void setIdNiveauDepart(Integer idNiveauDepart) {
		this.idNiveauDepart = idNiveauDepart;
	}

	public Integer getIdNiveauDepart() {
		return idNiveauDepart;
	}

	public void setIdNiveauArrive(Integer idNiveauArrive) {
		this.idNiveauArrive = idNiveauArrive;
	}

	public Integer getIdNiveauArrive() {
		return idNiveauArrive;
	}

	public void setMultipliant(Double multipliant) {
		this.multipliant = multipliant;
	}

	public Double getMultipliant() {
		return multipliant;
	}

	public void setDateDebut(LocalDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	public void setDateFin(LocalDateTime dateFin) {
		this.dateFin = dateFin;
	}

	public LocalDateTime getDateFin() {
		return dateFin;
	}

	public void setNomPoste(String nomPoste) {
		this.nomPoste = nomPoste;
	}

	public String getNomPoste() {
		return nomPoste;
	}

	public void setNomNiveauDepart(String nomNiveauDepart) {
		this.nomNiveauDepart = nomNiveauDepart;
	}

	public String getNomNiveauDepart() {
		return nomNiveauDepart;
	}

	public void setNomNiveauArrive(String nomNiveauArrive) {
		this.nomNiveauArrive = nomNiveauArrive;
	}

	public String getNomNiveauArrive() {
		return nomNiveauArrive;
	}

	public static List<VMultiplicationSalarialEmploye> selectByDateBetween(
			Connection connection, LocalDateTime date) throws SQLException {
		List<VMultiplicationSalarialEmploye> vMultiplicationSalarialEmployes = new ArrayList<>();
		String query = "SELECT * FROM v_multiplication_salarial_employe WHERE date_debut <= ? AND date_fin >= ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setObject(1, date);
		statement.setObject(2, date);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			VMultiplicationSalarialEmploye vMultiplicationSalarialEmploye = new VMultiplicationSalarialEmploye(
					resultSet.getInt("id"),
					resultSet.getInt("id_poste"),
					resultSet.getInt("id_niveau_depart"),
					resultSet.getInt("id_niveau_arrive"),
					resultSet.getDouble("multipliant"),
					resultSet.getTimestamp("date_debut").toLocalDateTime(),
					resultSet.getTimestamp("date_fin").toLocalDateTime(),
					resultSet.getString("nom_poste"),
					resultSet.getString("nom_niveau_depart"),
					resultSet.getString("nom_niveau_arrive"));
			vMultiplicationSalarialEmployes.add(vMultiplicationSalarialEmploye);
		}
		statement.close();
		resultSet.close();
		return vMultiplicationSalarialEmployes;
	}

}