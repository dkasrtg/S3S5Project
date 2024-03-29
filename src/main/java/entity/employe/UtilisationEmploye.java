package entity.employe;

import com.genericdao.*;
import com.genericdao.annotation.*;

import exception.DateAfterNowException;
import exception.DateFinAfterDateDebutException;
import exception.FieldNegatifZeroException;

import java.time.LocalDateTime;

@Table(name = "utilisation_employe")
public class UtilisationEmploye extends GenericDAO {

	@Id(autoGenerated = true)
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

	public UtilisationEmploye() {

	}

	public UtilisationEmploye(Integer id, Integer idMouvementMeuble, LocalDateTime dateDebut, LocalDateTime dateFin,
			Integer idRoleEmploye, Double dureeUtilisation, Double salaireTotal, String description) throws Exception {
		setId(id);
		setIdMouvementMeuble(idMouvementMeuble);
		setDateDebut(dateDebut);
		setDateFin(dateFin);
		setIdRoleEmploye(idRoleEmploye);
		setDureeUtilisation(dureeUtilisation);
		setSalaireTotal(salaireTotal);
		setDescription(description);

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

	public void setDateDebut(LocalDateTime dateDebut) throws Exception {
		if (dateDebut.isAfter(LocalDateTime.now())) {
			throw new DateAfterNowException();
		}
		this.dateDebut = dateDebut;
	}

	public LocalDateTime getDateDebut() {
		return dateDebut;
	}

	public void setDateFin(LocalDateTime dateFin) throws Exception {
		if (dateFin.isAfter(LocalDateTime.now())) {
			throw new DateAfterNowException();
		}
		if (dateFin.compareTo(getDateDebut()) <= 0) {
			throw new DateFinAfterDateDebutException();
		}
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

	public void setDureeUtilisation(Double dureeUtilisation) throws Exception{
		if (dureeUtilisation<=0) {
			throw new FieldNegatifZeroException("Duree d utilisation");
		}
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

}