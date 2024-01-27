package entity.employe;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.genericdao.GenericDAO;
import com.genericdao.annotation.Column;
import com.genericdao.annotation.Table;


@Table( name = "v_role_employe" )
public class VRoleEmploye extends GenericDAO {

    @Column( name = "id")
	private Integer id;

	@Column( name = "id_employe")
	private Integer idEmploye;

	@Column( name = "id_poste")
	private Integer idPoste;

	@Column( name = "id_niveau")
	private Integer idNiveau;

	@Column( name = "date_debut")
	private LocalDateTime dateDebut;

	@Column( name = "date_fin")
	private LocalDateTime dateFin;

	@Column( name = "taux_horaire")
	private Double tauxHoraire;

	@Column( name = "nom_employe")
	private String nomEmploye;

	@Column( name = "prenom_employe")
	private String prenomEmploye;

	@Column( name = "date_naissance_employe")
	private LocalDate dateNaissanceEmploye;

	@Column( name = "id_genre_employe")
	private Integer idGenreEmploye;

	@Column( name = "date_entree_employe")
	private LocalDateTime dateEntreeEmploye;

	@Column( name = "nom_genre_employe")
	private String nomGenreEmploye;

	@Column( name = "nom_poste")
	private String nomPoste;

	@Column( name = "nom_niveau")
	private String nomNiveau;

	@Column( name = "ordre_niveau")
	private Integer ordreNiveau;

	

    public VRoleEmploye() {

    }

    public VRoleEmploye(Integer id, Integer idEmploye, Integer idPoste, Integer idNiveau, LocalDateTime dateDebut, LocalDateTime dateFin, Double tauxHoraire, String nomEmploye, String prenomEmploye, LocalDate dateNaissanceEmploye, Integer idGenreEmploye, LocalDateTime dateEntreeEmploye, String nomGenreEmploye, String nomPoste, String nomNiveau, Integer ordreNiveau) {
        setId(id);
		setIdEmploye(idEmploye);
		setIdPoste(idPoste);
		setIdNiveau(idNiveau);
		setDateDebut(dateDebut);
		setDateFin(dateFin);
		setTauxHoraire(tauxHoraire);
		setNomEmploye(nomEmploye);
		setPrenomEmploye(prenomEmploye);
		setDateNaissanceEmploye(dateNaissanceEmploye);
		setIdGenreEmploye(idGenreEmploye);
		setDateEntreeEmploye(dateEntreeEmploye);
		setNomGenreEmploye(nomGenreEmploye);
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

	public void setDateNaissanceEmploye(LocalDate dateNaissanceEmploye) {
		this.dateNaissanceEmploye = dateNaissanceEmploye;
	}

	public LocalDate getDateNaissanceEmploye() {
		return dateNaissanceEmploye;
	}

	public void setIdGenreEmploye(Integer idGenreEmploye) {
		this.idGenreEmploye = idGenreEmploye;
	}

	public Integer getIdGenreEmploye() {
		return idGenreEmploye;
	}

	public void setDateEntreeEmploye(LocalDateTime dateEntreeEmploye) {
		this.dateEntreeEmploye = dateEntreeEmploye;
	}

	public LocalDateTime getDateEntreeEmploye() {
		return dateEntreeEmploye;
	}

	public void setNomGenreEmploye(String nomGenreEmploye) {
		this.nomGenreEmploye = nomGenreEmploye;
	}

	public String getNomGenreEmploye() {
		return nomGenreEmploye;
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

	public static List<VRoleEmploye> selectByIdEmploye(Connection connection,Integer idEmploye)throws Exception{
		String query = "select * from v_role_employe where id_employe = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, idEmploye);
		return VRoleEmploye.selectMultipleByPreparedStatement(VRoleEmploye.class, preparedStatement, connection);
	}

	public static List<VRoleEmploye> lessNearestFromDate(Connection connection , LocalDateTime localDateTime) throws Exception{
		String query = "WITH RankedRoles AS (\r\n" + //
				"    SELECT\r\n" + //
				"         id,\r\n" + //
				" id_employe ,           \r\n" + //
				" id_poste    ,          \r\n" + //
				" id_niveau    ,         \r\n" + //
				" date_debut    ,        \r\n" + //
				" date_fin       ,       \r\n" + //
				" taux_horaire    ,      \r\n" + //
				" nom_employe      ,     \r\n" + //
				" prenom_employe    ,    \r\n" + //
				" date_naissance_employe,\r\n" + //
				" id_genre_employe      ,\r\n" + //
				" date_entree_employe   ,\r\n" + //
				" nom_genre_employe     ,\r\n" + //
				" nom_poste             ,\r\n" + //
				" nom_niveau            ,\r\n" + //
				" ordre_niveau          ,\r\n" + //
				"        ROW_NUMBER() OVER (PARTITION BY id_employe ORDER BY date_debut DESC) AS rnk\r\n" + //
				"    FROM v_role_employe\r\n" + //
				"    WHERE date_debut <= ?\r\n" + //
				")\r\n" + //
				"SELECT *\r\n" + //
				"FROM RankedRoles\r\n" + //
				"WHERE rnk = 1;\r\n" + //
				"";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setObject(1, localDateTime);
		return VRoleEmploye.selectMultipleByPreparedStatement(VRoleEmploye.class, preparedStatement, connection);
	}

	public static List<VRoleEmploye> selectByDateFin(Connection connection, LocalDateTime dateFin) throws Exception{
		String query = "select * from v_role_employe where date_fin = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setObject(1, dateFin);
		return VRoleEmploye.selectMultipleByPreparedStatement(VRoleEmploye.class, preparedStatement, connection);
	}

}