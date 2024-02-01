package entity.employe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

import com.genericdao.GenericDAO;
import com.genericdao.annotation.Column;
import com.genericdao.annotation.Id;
import com.genericdao.annotation.Table;

import exception.FieldNegatifZeroException;

@Table(name = "role_employe")
public class RoleEmploye extends GenericDAO {

	@Id(autoGenerated = true)
	@Column(name = "id")
	private Integer id;

	@Column(name = "id_employe")
	private Integer idEmploye;

	@Column(name = "id_poste")
	private Integer idPoste;

	@Column(name = "id_niveau")
	private Integer idNiveau;

	@Column(name = "date_debut")
	private LocalDateTime dateDebut;

	@Column(name = "date_fin")
	private LocalDateTime dateFin;

	@Column(name = "taux_horaire")
	private Double tauxHoraire;

	public RoleEmploye() {

	}

	public RoleEmploye(Integer id, Integer idEmploye, Integer idPoste, Integer idNiveau, LocalDateTime dateDebut,
			LocalDateTime dateFin, Double tauxHoraire) throws Exception{
		setId(id);
		setIdEmploye(idEmploye);
		setIdPoste(idPoste);
		setIdNiveau(idNiveau);
		setDateDebut(dateDebut);
		setDateFin(dateFin);
		setTauxHoraire(tauxHoraire);

	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setIdEmploye(Integer idEmploye) {
		this.idEmploye = idEmploye;
	}

	public Integer getIdEmploye() {
		return idEmploye;
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

	public void setDateDebut(LocalDateTime dateDebut){
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

	public void setTauxHoraire(Double tauxHoraire) throws Exception{
		if (tauxHoraire<=0) {
			throw new FieldNegatifZeroException("Taux Horaire");
		}
		this.tauxHoraire = tauxHoraire;
	}

	public Double getTauxHoraire() {
		return tauxHoraire;
	}

	public static RoleEmploye selectByIdEmployeAndDateFin(Connection connection, Integer idEmploye,
			LocalDateTime dateFin) throws Exception {
		String query = "select * from role_employe where id_employe = ? and date_fin = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, idEmploye);
		preparedStatement.setObject(2, dateFin);
		return RoleEmploye.selectOneByPreparedStatement(RoleEmploye.class, preparedStatement, connection);
	}

}