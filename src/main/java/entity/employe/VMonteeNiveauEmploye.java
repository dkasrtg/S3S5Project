package entity.employe;

import com.genericdao.*;
import com.genericdao.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "v_montee_niveau_employe")
public class VMonteeNiveauEmploye extends GenericDAO {

	@Column(name = "id")
	private Integer id;

	@Column(name = "id_poste")
	private Integer idPoste;

	@Column(name = "id_niveau_depart")
	private Integer idNiveauDepart;

	@Column(name = "id_niveau_arrive")
	private Integer idNiveauArrive;

	@Column(name = "duree")
	private Double duree;

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

	public VMonteeNiveauEmploye() {

	}

	public VMonteeNiveauEmploye(Integer id, Integer idPoste, Integer idNiveauDepart, Integer idNiveauArrive,
			Double duree, LocalDateTime dateDebut, LocalDateTime dateFin, String nomPoste, String nomNiveauDepart,
			String nomNiveauArrive) {
		setId(id);
		setIdPoste(idPoste);
		setIdNiveauDepart(idNiveauDepart);
		setIdNiveauArrive(idNiveauArrive);
		setDuree(duree);
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

	public void setDuree(Double duree) {
		this.duree = duree;
	}

	public Double getDuree() {
		return duree;
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

	public static List<VMonteeNiveauEmploye> selectByDateBetween(
			Connection connection, LocalDateTime date) throws SQLException {
		List<VMonteeNiveauEmploye> monteeNiveauList = new ArrayList<>();
		String query = "SELECT * FROM v_montee_niveau_employe WHERE date_debut <= ? AND date_fin >= ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setObject(1, date);
		statement.setObject(2, date);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			VMonteeNiveauEmploye monteeNiveau = new VMonteeNiveauEmploye(
					resultSet.getInt("id"),
					resultSet.getInt("id_poste"),
					resultSet.getInt("id_niveau_depart"),
					resultSet.getInt("id_niveau_arrive"),
					resultSet.getDouble("duree"),
					resultSet.getTimestamp("date_debut").toLocalDateTime(),
					resultSet.getTimestamp("date_fin").toLocalDateTime(),
					resultSet.getString("nom_poste"),
					resultSet.getString("nom_niveau_depart"),
					resultSet.getString("nom_niveau_arrive"));
			monteeNiveauList.add(monteeNiveau);
		}
		statement.close();
		resultSet.close();
		return monteeNiveauList;
	}

}