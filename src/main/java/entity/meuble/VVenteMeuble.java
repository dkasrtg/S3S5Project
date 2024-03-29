package entity.meuble;

import com.genericdao.*;
import com.genericdao.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "v_vente_meuble")
public class VVenteMeuble extends GenericDAO {

	@Id(autoGenerated = false)
	@Column(name = "id")
	private Integer id;

	@Column(name = "date_vente")
	private LocalDateTime dateVente;

	@Column(name = "id_client")
	private Integer idClient;

	@Column(name = "prix_total")
	private Double prixTotal;

	@Column(name = "nom_client")
	private String nomClient;

	@Column(name = "prenom_client")
	private String prenomClient;

	public VVenteMeuble() {

	}

	public VVenteMeuble(Integer id, LocalDateTime dateVente, Integer idClient, Double prixTotal, String nomClient,
			String prenomClient) {
		setId(id);
		setDateVente(dateVente);
		setIdClient(idClient);
		setPrixTotal(prixTotal);
		setNomClient(nomClient);
		setPrenomClient(prenomClient);

	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setDateVente(LocalDateTime dateVente) {
		this.dateVente = dateVente;
	}

	public LocalDateTime getDateVente() {
		return dateVente;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	public Integer getIdClient() {
		return idClient;
	}

	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public Double getPrixTotal() {
		return prixTotal;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}

	public String getPrenomClient() {
		return prenomClient;
	}

	public static List<VVenteMeuble> selectByDate(Connection connection, LocalDateTime dateDebut, LocalDateTime dateFin)
			throws Exception {
		String query = "select * from v_vente_meuble where date_vente >= ? and date_vente <= ? ";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setObject(1, dateDebut);
		preparedStatement.setObject(2, dateFin);
		return VVenteMeuble.selectMultipleByPreparedStatement(VVenteMeuble.class, preparedStatement, connection);
	}

}