package entity.employe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;

import com.genericdao.GenericDAO;
import com.genericdao.annotation.Column;
import com.genericdao.annotation.Table;

@Table(name = "v_base_taux_horaire")
public class VBaseTauxHoraire extends GenericDAO {

	@Column(name = "id")
	private Integer id;

	@Column(name = "id_poste")
	private Integer idPoste;

	@Column(name = "date_debut")
	private LocalDateTime dateDebut;

	@Column(name = "date_fin")
	private LocalDateTime dateFin;

	@Column(name = "valeur")
	private Double valeur;

	@Column(name = "nom_poste")
	private String nomPoste;

	private List<Double> tauxBaseNiveaus;

	public VBaseTauxHoraire() {

	}

	public VBaseTauxHoraire(Integer id, Integer idPoste, LocalDateTime dateDebut, LocalDateTime dateFin, Double valeur,
			String nomPoste) {
		setId(id);
		setIdPoste(idPoste);
		setDateDebut(dateDebut);
		setDateFin(dateFin);
		setValeur(valeur);
		setNomPoste(nomPoste);

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

	public void setValeur(Double valeur) {
		this.valeur = valeur;
	}

	public Double getValeur() {
		return valeur;
	}

	public void setNomPoste(String nomPoste) {
		this.nomPoste = nomPoste;
	}

	public String getNomPoste() {
		return nomPoste;
	}

	public void setTauxBaseNiveaus(List<Double> tauxBaseNiveaus) {
		this.tauxBaseNiveaus = tauxBaseNiveaus;
	}

	public List<Double> getTauxBaseNiveaus() {
		return tauxBaseNiveaus;
	}

	public static List<VBaseTauxHoraire> selectByDateFin(Connection connection, LocalDateTime dateFin) throws Exception {
		String query = "select * from v_base_taux_horaire where date_fin = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setObject(1, dateFin);
		return VBaseTauxHoraire.selectMultipleByPreparedStatement(VBaseTauxHoraire.class, preparedStatement, connection);
	}

}