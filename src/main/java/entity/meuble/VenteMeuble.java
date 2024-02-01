package entity.meuble;


import com.genericdao.*;
import com.genericdao.annotation.*;

import exception.DateAfterNowException;

import java.time.LocalDateTime;


@Table( name = "vente_meuble" )
public class VenteMeuble extends GenericDAO {

    @Id( autoGenerated = true)
	@Column( name = "id")
	private Integer id;

	@Column( name = "date_vente")
	private LocalDateTime dateVente;

	@Column( name = "id_client")
	private Integer idClient;

	@Column( name = "prix_total")
	private Double prixTotal;

	

    public VenteMeuble() {

    }

    public VenteMeuble(Integer id, LocalDateTime dateVente, Integer idClient, Double prixTotal) throws Exception {
        setId(id);
		setDateVente(dateVente);
		setIdClient(idClient);
		setPrixTotal(prixTotal);
		
    }

    public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setDateVente(LocalDateTime dateVente) throws Exception{
		if (dateVente.isAfter(LocalDateTime.now())) {
			throw new DateAfterNowException();
		}
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

	

}