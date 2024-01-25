package entity.meuble;


import com.genericdao.*;
import com.genericdao.annotation.*;

import java.time.LocalDateTime;


@Table( name = "v_vente_meuble" )
public class VVenteMeuble extends GenericDAO {

    @Column( name = "id")
	private Integer id;

	@Column( name = "date_vente")
	private LocalDateTime dateVente;

	@Column( name = "id_client")
	private Integer idClient;

	@Column( name = "prix_total")
	private Double prixTotal;

	@Column( name = "nom_client")
	private String nomClient;

	@Column( name = "prenom_client")
	private String prenomClient;

	

    public VVenteMeuble() {

    }

    public VVenteMeuble(Integer id, LocalDateTime dateVente, Integer idClient, Double prixTotal, String nomClient, String prenomClient) {
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

	

}