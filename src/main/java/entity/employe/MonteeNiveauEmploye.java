package entity.employe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;

import com.genericdao.GenericDAO;
import com.genericdao.annotation.Column;
import com.genericdao.annotation.Id;
import com.genericdao.annotation.Table;

@Table(name = "montee_niveau_employe")
public class MonteeNiveauEmploye extends GenericDAO {

	@Id(autoGenerated = true)
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

	public MonteeNiveauEmploye() {

	}

	public MonteeNiveauEmploye(Integer id, Integer idPoste, Integer idNiveauDepart, Integer idNiveauArrive,
			Double duree, LocalDateTime dateDebut, LocalDateTime dateFin) {
		setId(id);
		setIdPoste(idPoste);
		setIdNiveauDepart(idNiveauDepart);
		setIdNiveauArrive(idNiveauArrive);
		setDuree(duree);
		setDateDebut(dateDebut);
		setDateFin(dateFin);

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

	public static MonteeNiveauEmploye selectByIdPosteNiveauDepartNiveauArriveDateFin(
			Connection connection, Integer idPoste, Integer idNiveauDepart, Integer idNiveauArrive,
			LocalDateTime dateFin)
			throws Exception {
		String query = "SELECT * FROM montee_niveau_employe WHERE id_poste = ? AND id_niveau_depart = ? AND id_niveau_arrive = ? AND date_fin = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, idPoste);
		statement.setInt(2, idNiveauDepart);
		statement.setInt(3, idNiveauArrive);
		statement.setObject(4, dateFin);
		return MonteeNiveauEmploye.selectOneByPreparedStatement(MonteeNiveauEmploye.class, statement, connection);
	}

}