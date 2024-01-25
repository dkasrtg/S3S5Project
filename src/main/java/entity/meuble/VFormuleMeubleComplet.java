package entity.meuble;


import java.util.List;

import com.genericdao.*;
import com.genericdao.annotation.*;



@Table( name = "v_formule_meuble_complet" )
public class VFormuleMeubleComplet extends GenericDAO {

    @Column( name = "id")
	private Integer id;

	@Column( name = "id_meuble")
	private Integer idMeuble;

	@Column( name = "id_taille_meuble")
	private Integer idTailleMeuble;

	@Column( name = "nom_meuble")
	private String nomMeuble;

	@Column( name = "nom_taille_meuble")
	private String nomTailleMeuble;

	private List<VTotalVenteProduitGenre> vTotalVenteProduitGenres;

	

    public VFormuleMeubleComplet() {

    }

    public VFormuleMeubleComplet(Integer id, Integer idMeuble, Integer idTailleMeuble, String nomMeuble, String nomTailleMeuble) {
        setId(id);
		setIdMeuble(idMeuble);
		setIdTailleMeuble(idTailleMeuble);
		setNomMeuble(nomMeuble);
		setNomTailleMeuble(nomTailleMeuble);
		
    }

    public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setIdMeuble(Integer idMeuble) {
		this.idMeuble = idMeuble;
	}

	public Integer getIdMeuble() {
		return idMeuble;
	}

	public void setIdTailleMeuble(Integer idTailleMeuble) {
		this.idTailleMeuble = idTailleMeuble;
	}

	public Integer getIdTailleMeuble() {
		return idTailleMeuble;
	}

	public void setNomMeuble(String nomMeuble) {
		this.nomMeuble = nomMeuble;
	}

	public String getNomMeuble() {
		return nomMeuble;
	}

	public void setNomTailleMeuble(String nomTailleMeuble) {
		this.nomTailleMeuble = nomTailleMeuble;
	}

	public String getNomTailleMeuble() {
		return nomTailleMeuble;
	}

	public void setvTotalVenteProduitGenres(List<VTotalVenteProduitGenre> vTotalVenteProduitGenres) {
		this.vTotalVenteProduitGenres = vTotalVenteProduitGenres;
	}

	public List<VTotalVenteProduitGenre> getvTotalVenteProduitGenres() {
		return vTotalVenteProduitGenres;
	}

	

}