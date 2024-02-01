package entity.employe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

import com.genericdao.GenericDAO;
import com.genericdao.annotation.Column;
import com.genericdao.annotation.Id;
import com.genericdao.annotation.Table;

import exception.DateAfterNowException;
import exception.FieldNegatifZeroException;

@Table(name = "base_taux_horaire")
public class BaseTauxHoraire extends GenericDAO {

	@Id(autoGenerated = true)
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

	public BaseTauxHoraire() {

	}

	public BaseTauxHoraire(Integer id, Integer idPoste, LocalDateTime dateDebut, LocalDateTime dateFin, Double valeur) throws Exception {
		setId(id);
		setIdPoste(idPoste);
		setDateDebut(dateDebut);
		setDateFin(dateFin);
		setValeur(valeur);

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

	public void setDateDebut(LocalDateTime dateDebut) throws Exception{
		if (dateDebut.isAfter(LocalDateTime.now())) {
			throw new DateAfterNowException();
		}
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

	public void setValeur(Double valeur) throws Exception{
		if (valeur<=0) {
			throw new FieldNegatifZeroException("Valeur");
		}
		this.valeur = valeur;
	}

	public Double getValeur() {
		return valeur;
	}

	public static BaseTauxHoraire selectByIdPosteAndDateFin(Connection connection, Integer idPoste,
			LocalDateTime dateFin) throws Exception {
		String query = "select * from base_taux_horaire where id_poste = ? and date_fin = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, idPoste);
		preparedStatement.setObject(2, dateFin);
		return BaseTauxHoraire.selectOneByPreparedStatement(BaseTauxHoraire.class, preparedStatement, connection);
	}

}