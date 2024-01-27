package entity.employe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;

import com.genericdao.GenericDAO;
import com.genericdao.annotation.Column;
import com.genericdao.annotation.Table;

@Table(name = "v_utilisation_employe")
public class VUtilisationEmploye extends GenericDAO {

	@Column(name = "id")
	private Integer id;

	@Column(name = "id_mouvement_meuble")
	private Integer idMouvementMeuble;

	@Column(name = "date_debut")
	private LocalDateTime dateDebut;

	@Column(name = "date_fin")
	private LocalDateTime dateFin;

	@Column(name = "id_role_employe")
	private Integer idRoleEmploye;

	@Column(name = "duree_utilisation")
	private Double dureeUtilisation;

	@Column(name = "salaire_total")
	private Double salaireTotal;

	@Column(name = "description")
	private String description;

	@Column(name = "id_employe")
	private Integer idEmploye;

	@Column(name = "id_poste")
	private Integer idPoste;

	@Column(name = "id_niveau")
	private Integer idNiveau;

	@Column(name = "taux_horaire")
	private Double tauxHoraire;

	@Column(name = "nom_employe")
	private String nomEmploye;

	@Column(name = "prenom_employe")
	private String prenomEmploye;

	@Column(name = "nom_genre_employe")
	private String nomGenreEmploye;

	@Column(name = "id_genre_employe")
	private Integer idGenreEmploye;

	@Column(name = "nom_poste")
	private String nomPoste;

	@Column(name = "nom_niveau")
	private String nomNiveau;

	public VUtilisationEmploye() {

	}

	public VUtilisationEmploye(Integer id, Integer idMouvementMeuble, LocalDateTime dateDebut, LocalDateTime dateFin,
			Integer idRoleEmploye, Double dureeUtilisation, Double salaireTotal, String description, Integer idEmploye,
			Integer idPoste, Integer idNiveau, Double tauxHoraire, String nomEmploye, String prenomEmploye,
			String nomGenreEmploye, Integer idGenreEmploye, String nomPoste, String nomNiveau) {
		setId(id);
		setIdMouvementMeuble(idMouvementMeuble);
		setDateDebut(dateDebut);
		setDateFin(dateFin);
		setIdRoleEmploye(idRoleEmploye);
		setDureeUtilisation(dureeUtilisation);
		setSalaireTotal(salaireTotal);
		setDescription(description);
		setIdEmploye(idEmploye);
		setIdPoste(idPoste);
		setIdNiveau(idNiveau);
		setTauxHoraire(tauxHoraire);
		setNomEmploye(nomEmploye);
		setPrenomEmploye(prenomEmploye);
		setNomGenreEmploye(nomGenreEmploye);
		setIdGenreEmploye(idGenreEmploye);
		setNomPoste(nomPoste);
		setNomNiveau(nomNiveau);

	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setIdMouvementMeuble(Integer idMouvementMeuble) {
		this.idMouvementMeuble = idMouvementMeuble;
	}

	public Integer getIdMouvementMeuble() {
		return idMouvementMeuble;
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

	public void setIdRoleEmploye(Integer idRoleEmploye) {
		this.idRoleEmploye = idRoleEmploye;
	}

	public Integer getIdRoleEmploye() {
		return idRoleEmploye;
	}

	public void setDureeUtilisation(Double dureeUtilisation) {
		this.dureeUtilisation = dureeUtilisation;
	}

	public Double getDureeUtilisation() {
		return dureeUtilisation;
	}

	public void setSalaireTotal(Double salaireTotal) {
		this.salaireTotal = salaireTotal;
	}

	public Double getSalaireTotal() {
		return salaireTotal;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
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

	public void setTauxHoraire(Double tauxHoraire) {
		this.tauxHoraire = tauxHoraire;
	}

	public Double getTauxHoraire() {
		return tauxHoraire;
	}

	public void setNomEmploye(String nomEmploye) {
		this.nomEmploye = nomEmploye;
	}

	public String getNomEmploye() {
		return nomEmploye;
	}

	public void setPrenomEmploye(String prenomEmploye) {
		this.prenomEmploye = prenomEmploye;
	}

	public String getPrenomEmploye() {
		return prenomEmploye;
	}

	public void setNomGenreEmploye(String nomGenreEmploye) {
		this.nomGenreEmploye = nomGenreEmploye;
	}

	public String getNomGenreEmploye() {
		return nomGenreEmploye;
	}

	public void setIdGenreEmploye(Integer idGenreEmploye) {
		this.idGenreEmploye = idGenreEmploye;
	}

	public Integer getIdGenreEmploye() {
		return idGenreEmploye;
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

	public static List<VUtilisationEmploye> selectByIdMouvementMeuble(Connection connection, Integer idMouvementMeuble)
			throws Exception {
		String query = "select * from v_utilisation_employe where id_mouvement_meuble = ? ";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, idMouvementMeuble);
		return VUtilisationEmploye.selectMultipleByPreparedStatement(VUtilisationEmploye.class, preparedStatement,
				connection);
	}

}